package com.mallcai.fulfillment.pe.biz.service.impl.query;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.api.service.query.TransDetailService;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.GoodTransInfo;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.WareTransInfo;
import com.mallcai.fulfillment.pe.biz.service.bo.TransDbDataBo;
import com.mallcai.fulfillment.pe.biz.service.util.BaseBeanUtil;
import com.mallcai.fulfillment.pe.common.bo.StoreWareInfoBo;
import com.mallcai.fulfillment.pe.dependency.client.StoreServiceClient;
import com.mallcai.fulfillment.pe.dependency.enums.WareHouseTypeEnum;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("transDetailService")
@Slf4j
public class TransDetailServiceImpl implements TransDetailService {


    @ConfigValue(key="/app_fulfillment/common/tms.data.config")
    private Map<String,Object> tmsConfig;

    @Qualifier("fdaNamedTemplate")
    @Autowired
    NamedParameterJdbcTemplate fdaNamedTemplate;

    @Qualifier("fpeNamedTemplate")
    @Autowired
    NamedParameterJdbcTemplate fpeNamedTemplate;

    @Autowired
    StoreServiceClient storeServiceClient;

    @Autowired
    IcProductGoodsServiceFacade icProductGoodsServiceFacade;


    @Override
    public PlainResult<List<WareTransInfo>> standTransPlan(List<Integer> storeIds, Date date) {
        //按照门店维度，履约侧在当天18点履约获取取货日期为T+1（T为执行日期）的标品订单销量数据。
        //口径 select count(1) from pe_order where pickup_time ='2019-11-12'
        //and group_value=1 and store_id in (265,153,92) and status not in  (-1,5)

        Map<String, Object> params = Maps.newHashMap();
        params.put("pickupTime", DateUtils.addDay(date,1));
        params.put("groupValue", GroupValueEnum.ORDER_TYPE_STANDARD.getGroupValue());
        params.put("storeIds",storeIds);
        params.put("createTime",new Date());

        List<WareTransInfo> result = findStoreGoodTotal(params);

        if(tmsConfig.containsKey("standRadio")){
            Double radio = (Double) tmsConfig.get("standRadio");
            result.stream().forEach(ware->{
                ware.setSoldCount(ware.getSoldCount().multiply(new BigDecimal(radio)));
                ware.getGoodsList().stream().forEach(good->{
                    good.setSkuCount(good.getSkuCount().multiply(new BigDecimal(radio)));
                    good.setSkuWeight(good.getSkuWeight().multiply(new BigDecimal(radio)));
                });
            });
        }

        return PlainResult.ok(result);
    }

    @Override
    public PlainResult<List<WareTransInfo>> fronzeTransPlan(List<Integer> storeIds, Date date) {
        // 按照门店维度，履约侧在当天18点出具T天16点30分（T为执行日期）的冻品订单销量数据和前30天16点30分-20点平均冻品订单销量数据。
        //口径 select count(1) from pe_order where expect_push_time ='2019-11-13'
        //and group_value=2 and city_id in (265,153,92) and status not in  (-1,5)
        //and create_time < '2019-11-13 16:30:00'

        Map<String, Object> params = Maps.newHashMap();
        params.put("pushTime", date);
        params.put("groupValue", GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue());
        params.put("storeIds",storeIds);
        String endTime=tmsConfig.containsKey("frozenBeg")?(String)tmsConfig.get("frozenBeg"):"16:30:00";
        Date createTime = DateUtils.parseDate(DateUtils.formatDate(date) + " "+endTime, DateUtils.COMPLETE_DATE_PATTERN);
        params.put("createTime",createTime);

        // 冻品实时数据
        List<WareTransInfo> realTotal = findStoreGoodTotal(params);

        return PlainResult.ok(realTotal);
    }

    @Override
    public PlainResult<List<WareTransInfo>> freshTransPlan(List<Integer> storeIds, Date date) {
        //参照分拣预测，按照门店维度，大数据在当天18点出具T天（T为执行日期）的生鲜预测量
        // 口径 select count(1) from pe_order where expect_push_time ='2019-11-13'
        //and group_value=3 and city_id in (265,153,92) and status not in  (-1,5)

        Map<String, Object> params = Maps.newHashMap();
        params.put("pushTime", date);
        params.put("groupValue", GroupValueEnum.FRESH_PRODUCT.getGroupValue());
        params.put("storeIds",storeIds);
        params.put("createTime",new Date());

        return PlainResult.ok(findStoreGoodTotal(params));
    }




    private List<WareTransInfo> findStoreGoodTotal(Map<String,Object> params){

        String execSql="select city_id,store_id,city_product_id,sum(substring_index(substring_index(commodity_info_ext,'productNum\":',-1),'}',1)) AS product_num from pe_order \n" +
                "where group_value=:groupValue and store_id in (:storeIds) and status not in  (-1,5) \n" +
                "and create_time <= :createTime \n";
        if(params.containsKey("pushTime")){
            execSql =  execSql + "and expect_push_time =:pushTime \n";
        }
        if(params.containsKey("pickupTime")){
            execSql =  execSql + "and pickup_time =:pickupTime \n";
        }
        execSql = execSql +"group by city_product_id,store_id";

        // 门店+ 商品 集单
        log.info("tms sql:{}",execSql);
        log.info("tms sql param:{}", JSON.toJSONString(params));
        long beg=System.currentTimeMillis();
        List<TransDbDataBo> infos = fdaNamedTemplate.query(execSql, params, new RowMapper<TransDbDataBo>() {
            @Override
            public TransDbDataBo mapRow(ResultSet resultSet, int i) throws SQLException {
                return TransDbDataBo.builder()
                        .cityId(resultSet.getInt("city_id"))
                        .storeId(resultSet.getInt("store_id"))
                        .cityProductId(resultSet.getInt("city_product_id"))
                        .productNum(resultSet.getBigDecimal("product_num"))
                        .build();
            }
        });
        log.info("exec sql costs:{}ms, result.size:{}", System.currentTimeMillis()-beg,infos.size());

        //如果是冻品 取冻品的平均数据添加到 infos ,后面步骤统一做合并
        if(params.get("groupValue").equals("2")){
            String mode = (String) tmsConfig.get("frozenMode");
            if("planA".equals(mode)) {
                infos.addAll(planAcacalList(params));
            }else if("planB".equals(mode)){
                infos.addAll(planBcacalList(params));
            }
        }

        //查询门店信息
        Set<Integer> stores = infos.stream().map(TransDbDataBo::getStoreId).collect(Collectors.toSet());
//        WareHouseTypeEnum type = WareHouseTypeEnum.explainType((String) params.get("groupValue"));
        Map<Integer, StoreWareInfoBo> wareInfos = storeServiceClient.findStoreToWareHouse(stores, WareHouseTypeEnum.WARE_HOUSE_FRESH);

        //构建门店信息
        Map<Integer, WareTransInfo> transInfoMap = stores.stream().collect(Collectors.toMap(Function.identity(), p -> {
            StoreWareInfoBo wareInfo = wareInfos.get(p);
            WareTransInfo info = new WareTransInfo();
            info.setPickupDate(DateUtils.startDate((Date) params.get("createTime")));
            info.setStoreId(p);
            if(wareInfo!=null) {
                info = BaseBeanUtil.convertObject(wareInfo, WareTransInfo.class);
                info.setWareCode(wareInfo.getWareCode());
                info.setPickupDate(DateUtils.startDate((Date) params.get("createTime")));
                info.setReceiver(wareInfo.getStoreManager());
                info.setTel(wareInfo.getStorePhone());
            }
            info.setGoodsList(Lists.newLinkedList());
            return info;
        }));

        //查询货品信息
        List<QueryGoodsRelBO> queryList = infos.stream().map(p-> BaseBeanUtil.convertObject(p,QueryGoodsRelBO.class)).distinct().collect(Collectors.toList());
        Map<Integer, ItemGoodsInfoBO> goodsMap = icProductGoodsServiceFacade.searchGoodsRel(queryList).stream()
                .collect(Collectors.toMap(p -> p.getCityProductId(), p -> p,(oldValue, newValue) -> newValue));

        //根据查询的商品->货品对应关系合并商品集合 (冻品的平均数据和平日时间也会在这里合并)
        Map<String, TransDbDataBo> mergeMap = infos.stream().filter(p -> goodsMap.containsKey(p.getCityProductId())).
                collect(Collectors.toMap(p -> goodsMap.get(p.getCityProductId()).getGoodsId(), p -> p,
                        (oldValue,newValue) -> {
                            oldValue.setProductNum(newValue.getProductNum().add(oldValue.getProductNum()));
                            return oldValue; }));

        // 构建门店的货品信息
        mergeMap.values().stream()
                .forEach(p->{
                    ItemGoodsInfoBO goodBo = goodsMap.get(p.getCityProductId());
                    GoodTransInfo good = new GoodTransInfo();
                    good.setSkuId(goodBo.getGoodsId());
                    good.setSkuName(goodBo.getGoodsName());
                    good.setSkuUnit(goodBo.getGoodsUnit());
                    good.setSkuCount(p.getProductNum().multiply(goodBo.getRelNum()));
                    good.setSkuWeight(BigDecimal.ZERO);
                    if(goodBo.getGoodsUnit().equals("KG")){
                        good.setSkuWeight(good.getSkuCount());
                    }
                    good.setSkuVolume(BigDecimal.ZERO);
                    transInfoMap.get(p.getStoreId()).getGoodsList().add(good);
                });
        List<WareTransInfo> wareResult = transInfoMap.values().stream().collect(Collectors.toList());
        //更新门店货品总数
        wareResult.stream().forEach(p->{
            BigDecimal sumCount = p.getGoodsList().stream().map(GoodTransInfo::getSkuCount).reduce(BigDecimal.ZERO, BigDecimal::add);
            p.setSoldCount(sumCount);
            BigDecimal sumWeight = p.getGoodsList().stream().map(GoodTransInfo::getSkuWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
            p.setSoldWeight(sumWeight);
        });
        return wareResult;
    }


    // plan B 从中间表读取 ,需要新建定时任务定时存取数据
    private List<TransDbDataBo> planBcacalList(Map<String,Object> params){
        String sumarySql="select store_id,city_product_id,AVG(product_sum) as product_sum from frozen_good_summary \n" +
                "where store_id in (:storeIds) and sum_date >= :sumDate \n" +
                "group by store_id,good_id ";


        List<TransDbDataBo> infos = fpeNamedTemplate.query(sumarySql, params, new RowMapper<TransDbDataBo>() {
            @Override
            public TransDbDataBo mapRow(ResultSet resultSet, int i) throws SQLException {
                return TransDbDataBo.builder()
                        .storeId(resultSet.getInt("store_id"))
                        .cityProductId(resultSet.getInt("city_product_id"))
                        .productNum(resultSet.getBigDecimal("product_sum"))
                        .build();
            }
        });

        return infos;
    }

    // plan A 直接从数据库读取,不用建定时任务,可能会耗时较长,( 需要建 create_time 的索引)
    public List<TransDbDataBo> planAcacalList(Map<String,Object> params){
        Date pushTime = (Date) params.get("pushTime");
        Integer day=tmsConfig.containsKey("frozenDayCnt")?(Integer) tmsConfig.get("frozenDayCnt"):30;
        String begTime=tmsConfig.containsKey("frozenBeg")?(String)tmsConfig.get("frozenBeg"):"16:30:00";
        String endTime=tmsConfig.containsKey("frozenEnd")?(String)tmsConfig.get("frozenEnd"):"20:00:00";

        String avgSql="select store_id,city_id,city_product_id,sum(SUBSTR(commodity_info_ext,INSTR(commodity_info_ext,'productNum\":')+12,INSTR(commodity_info_ext,',\"packageQuantity')-INSTR(commodity_info_ext,'productNum\":')-12))/"+day+" AS product_num from pe_order\n" +
                "where group_value=:groupValue and store_id in (:storeIds) and status not in  (-1,5) ";

        String timeCondition="";
        for (int i = 1; i <= day; i++) {
            Date periodBeg = DateUtils.parseDate(DateUtils.formatDate(DateUtils.addDay(pushTime,-i)) + " "+begTime, DateUtils.COMPLETE_DATE_PATTERN);
            Date periodEnd = DateUtils.parseDate(DateUtils.formatDate(DateUtils.addDay(pushTime,-i)) + " "+endTime, DateUtils.COMPLETE_DATE_PATTERN);
            params.put("periodBeg"+i,periodBeg);
            params.put("periodEnd"+i,periodEnd);
            if(i==1){
                timeCondition=timeCondition+"and (create_time BETWEEN :periodBeg"+i+" and :periodEnd"+i+" ";
            }else if(i==day){
                timeCondition = timeCondition+"or create_time BETWEEN :periodBeg"+i+" and :periodEnd"+i+" )";
            }else{
                timeCondition = timeCondition+"or create_time BETWEEN :periodBeg"+i+" and :periodEnd"+i+" ";
            }
        }
        avgSql =avgSql + timeCondition + "group by store_id,city_product_id";

        log.info("tms avg sql:{}",avgSql);
        log.info("tms avg param:{}", JSON.toJSONString(params));
        long beg=System.currentTimeMillis();
        List<TransDbDataBo> avgList = fdaNamedTemplate.query(avgSql, params, new RowMapper<TransDbDataBo>() {
            @Override
            public TransDbDataBo mapRow(ResultSet resultSet, int i) throws SQLException {
                return TransDbDataBo.builder()
                        .cityId(resultSet.getInt("city_id"))
                        .storeId(resultSet.getInt("store_id"))
                        .cityProductId(resultSet.getInt("city_product_id"))
                        .productNum(resultSet.getBigDecimal("product_num"))
                        .build();
            }
        });
        log.info("exec sql costs:{}ms, result.size:{}", System.currentTimeMillis()-beg,avgList.size());
        return avgList;
    }
}
