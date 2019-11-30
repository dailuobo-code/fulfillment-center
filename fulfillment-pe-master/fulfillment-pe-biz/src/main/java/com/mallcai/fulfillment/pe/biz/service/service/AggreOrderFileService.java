package com.mallcai.fulfillment.pe.biz.service.service;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.dailuobo.api.domain.entity.CityProduct;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.framework.redis.plugin.sequence.RedisIdFactory;
import com.mallcai.fulfillment.pe.biz.service.bo.*;
import com.mallcai.fulfillment.pe.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.pe.biz.service.util.BaseBeanUtil;
import com.mallcai.fulfillment.pe.biz.service.util.EasyExcelUtil;
import com.mallcai.fulfillment.pe.common.bo.StoreWareInfoBo;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.dependency.client.IcProductGoodsServiceClient;
import com.mallcai.fulfillment.pe.dependency.client.StoreServiceClient;
import com.mallcai.fulfillment.pe.dependency.enums.WareHouseTypeEnum;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AggreOrderFileService {

    @ConfigValue(key="/app_fulfillment/common/product.sql")
    private String productSql;

    @ConfigValue(key="/app_fulfillment/common/goods.sql")
    private String goodSql;

    @Value("${aggre.xsl.path}")
    private String xslPath;

    @Qualifier("fdaNamedTemplate")
    @Autowired
    NamedParameterJdbcTemplate fdaNamedTemplate;

    @Autowired
    GoodsGrayRouterService goodsGrayRouterService;

    @Autowired
    StoreServiceClient storeServiceClient;

    @Autowired
    RedisIdFactory redisIdFactory;

    @Autowired
    private IcProductGoodsServiceClient icProductGoodsServiceClient;

    @Autowired
    private IcProductGoodsServiceFacade icProductGoodsServiceFacade;

    public void AggreXlsFile(AnalysisAggregate anaInfo){

        if(anaInfo.getPushTime()==null){
            anaInfo.setPushTime(DateUtils.startDate(new Date()));
        }

        //根据nacos获取配置的sql  拼装sql
        Map<String, Object> params = Maps.newHashMap();
        params.put("pushTime",anaInfo.getPushTime());
        params.put("groupValue",anaInfo.getGroupValue());

        Long fileId = redisIdFactory.getIdByDay(Constants.XLS_FILENAME_KEY);
        String cityStr=CollectionUtils.isEmpty(anaInfo.getCityIds())?"ALL":anaInfo.getCityIds().stream().map(p->p.toString()).collect(Collectors.joining("_"));;
        String prodFileName=String.format("%s_%s_%s_%s_%03d.xlsx",DateUtils.formatDate(anaInfo.getPushTime(),DateUtils.YEAR_MONTH_DATE_PATTEN),
                anaInfo.getGroupValue(),cityStr,"prod",fileId);
        String goodFileName=String.format("%s_%s_%s_%s_%03d.xlsx",DateUtils.formatDate(anaInfo.getPushTime(),DateUtils.YEAR_MONTH_DATE_PATTEN),
                anaInfo.getGroupValue(),cityStr,"good",fileId);

        params.put("prodFileName",prodFileName);
        params.put("goodFileName",goodFileName);

        String conditionSql=" group_value = :groupValue and expect_push_time = :pushTime";


        if(anaInfo.getStauts()!=null){
            conditionSql = conditionSql + " and status = :status";
            params.put("status",anaInfo.getStauts());
        }else{
            conditionSql = conditionSql + " and status not in  (:status)";
            ArrayList<Object> list = Lists.newArrayList();
            list.add(-1);
            list.add(5);
            params.put("status", list);
        }

        if(anaInfo.getStartTime()!=null && anaInfo.getEndTime() !=null){
            conditionSql = conditionSql + " and create_time  >= :startTime and create_time < :endTime";
            params.put("startTime",anaInfo.getStartTime());
            params.put("endTime",anaInfo.getEndTime());
        }

        if(!CollectionUtils.isEmpty(anaInfo.getStoreIds())){
            conditionSql = conditionSql + " and store_id in (:storeList)";
            params.put("storeList",anaInfo.getStoreIds());
        }

        if(!CollectionUtils.isEmpty(anaInfo.getCityIds())){
            List<Integer> goodCitys = anaInfo.getCityIds().stream().filter(p -> goodsGrayRouterService.isNeedRoute(p)).collect(Collectors.toList());
            List<Integer> prodCitys = anaInfo.getCityIds().stream().filter(p -> !goodsGrayRouterService.isNeedRoute(p)).collect(Collectors.toList());

            if(!CollectionUtils.isEmpty(goodCitys)){
                conditionSql = conditionSql + " and city_id  in (:cityIds) ";
                params.put("cityIds",goodCitys);
                execGoodsSql(conditionSql,params);
            }

            if(!CollectionUtils.isEmpty(prodCitys)){
                conditionSql = conditionSql + " and city_id  in (:cityIds) ";
                params.put("cityIds",prodCitys);
                execProductSql(conditionSql,params);
            }
        }else{
            //货品白名单全部开放
            if(goodsGrayRouterService.isAllOpen()){
                execGoodsSql(conditionSql,params);
            }else{

                List<Integer> allCities = goodsGrayRouterService.getAllCities();
                params.put("cityIds",allCities);


                String goodCondSql = conditionSql + " and city_id  in (:cityIds) ";
                execGoodsSql(goodCondSql,params);

                String prodCondSql = conditionSql + " and city_id  not in (:cityIds) ";
                execProductSql(prodCondSql,params);

            }
        }

    }


    public void execProductSql(String condition,Map<String, Object> params){
        String execSql=productSql + condition + "group by store_id,city_product_id";
        log.info("exec prod export file sql:{}",execSql);
        log.info("exec prod export file params:{}",JSON.toJSONString(params));
        long beg=System.currentTimeMillis();
        List<AggregateDetail> infos = fdaNamedTemplate.query(execSql, params, new RowMapper<AggregateDetail>() {
            @Override
            public AggregateDetail mapRow(ResultSet resultSet, int i) throws SQLException {
                return AggregateDetail.builder()
                        .store(resultSet.getInt("store_id"))
                        .product(resultSet.getInt("city_product_id"))
                        .productNo(resultSet.getString("product_no"))
                        .num(resultSet.getInt("productNum"))
                        .cityId(resultSet.getInt("city_id"))
                        .build();
            }
        });
        log.info("exec prod export file ,result:{},costs:{} ms",infos.size(),System.currentTimeMillis()-beg);
        //构建xsl表格对象
        Set<Integer> stores = infos.stream().map(AggregateDetail::getStore).collect(Collectors.toSet());

        WareHouseTypeEnum type = WareHouseTypeEnum.explainType(params.get("groupValue").toString());
        Map<Integer, StoreWareInfoBo> wareInfos = storeServiceClient.findStoreToWareHouse(stores, type);

        // 查询商品名称  TODO
        Map<Integer, List<AggregateDetail>> details = infos.stream().collect(Collectors.groupingBy(AggregateDetail::getCityId));
        Map<Integer, List<Integer>> maps=Maps.newHashMap();
        details.entrySet().stream().forEach(
                p->maps.put(p.getKey(),p.getValue().stream().map(AggregateDetail::getProduct).distinct().collect(Collectors.toList()))
        );
        Map<Integer, CityProduct> productInfo = icProductGoodsServiceClient.searchProductInfo(maps);

//        log.info("请求Product4MgrVO 接口返回: {}",JSON.toJSONString(productInfo));

        List<AggreProdXLSModel> models = infos.stream().map(p -> {
            AggreProdXLSModel model = BaseBeanUtil.convertObject(p, AggreProdXLSModel.class);
            if(wareInfos.containsKey(p.getStore())) {
                model.setWarehouse(wareInfos.get(p.getStore()).getWareId());
                model.setWareHouseName(wareInfos.get(p.getStore()).getWareName());
                model.setStoreNo(wareInfos.get(p.getStore()).getStoreNo());
            }
            if(productInfo.containsKey(p.getProduct())) {
                model.setProductName(productInfo.get(p.getProduct()).getShowName());
            }
            model.setPushTime(DateUtils.format((Date) params.get("pushTime"),DateUtils.DEFAULT_DATE_TIME_PATTERN));
            return model;
        }).collect(Collectors.toList());

        try {
            OutputStream out = new FileOutputStream(xslPath+params.get("prodFileName"));
            beg=System.currentTimeMillis();
            EasyExcelUtil.writeExcelWithModel(out, models, AggreProdXLSModel.class, ExcelTypeEnum.XLSX);
            log.info("write prod file succ!! fileName:{},fileSize:{},cost:{} ms",xslPath+params.get("prodFileName"),models.size(),System.currentTimeMillis()-beg);
        }catch (Exception e){
            log.error("写入xls文件错误,错误信息:",e);
        }


    }


    public void execGoodsSql(String condition,Map<String, Object> params){
        String execSql=goodSql + condition + "group by store_id,city_product_id";
        log.info("exec good export file sql:{}",execSql);
        log.info("exec good export file params:{}",JSON.toJSONString(params));
        long beg=System.currentTimeMillis();
        List<AggregateGoodDetail> infos = fdaNamedTemplate.query(execSql, params, new RowMapper<AggregateGoodDetail>() {
            @Override
            public AggregateGoodDetail mapRow(ResultSet resultSet, int i) throws SQLException {
                return AggregateGoodDetail.builder()
                        .store(resultSet.getInt("store_id"))
                        .productNum(resultSet.getInt("product_num"))
                        .cityId(resultSet.getInt("city_id"))
                        .cityProductId(resultSet.getInt("city_product_id"))
                        .build();
            }
        });
        log.info("exec good export file result:{}, costs:{} ms",infos.size(),System.currentTimeMillis()-beg);
        //构建xsl表格对象
        Set<Integer> stores = infos.stream().map(AggregateGoodDetail::getStore).collect(Collectors.toSet());

        WareHouseTypeEnum type = WareHouseTypeEnum.explainType(params.get("groupValue").toString());
        Map<Integer, StoreWareInfoBo> wareInfos = storeServiceClient.findStoreToWareHouse(stores, type);

        //查询货品名称
        List<QueryGoodsRelBO> queryList = infos.stream().map(p-> BaseBeanUtil.convertObject(p,QueryGoodsRelBO.class)).collect(Collectors.toList());
        //如果商品中心没有货品信息,函数会抛异常
        Map<Integer, ItemGoodsInfoBO> goodsMap = icProductGoodsServiceFacade.searchGoodsRel(queryList).stream()
                .collect(Collectors.toMap(p -> p.getCityProductId(), p -> p,(oldValue, newValue) -> newValue));


        List<AggreGoodXLSModel> models = infos.stream().map(p -> {
            AggreGoodXLSModel model = BaseBeanUtil.convertObject(p, AggreGoodXLSModel.class);
            if(wareInfos.containsKey(p.getStore())){
                model.setWarehouse(wareInfos.get(p.getStore()).getWareId());
                model.setWarehouseName(wareInfos.get(p.getStore()).getWareName());
                model.setStoreNo(wareInfos.get(p.getStore()).getStoreNo());
            }else{
                model.setWarehouseName("-");
            }
            model.setPushTime(DateUtils.format((Date) params.get("pushTime"),DateUtils.DEFAULT_DATE_TIME_PATTERN));
            if(goodsMap.containsKey(p.getCityProductId())){
                model.setGoodsName(goodsMap.get(p.getCityProductId()).getGoodsName());
                model.setUnit(goodsMap.get(p.getCityProductId()).getGoodsUnit());
                model.setGoodsId(goodsMap.get(p.getCityProductId()).getGoodsId());
                BigDecimal goodWeight = goodsMap.get(p.getCityProductId()).getRelNum().multiply(new BigDecimal(p.getProductNum()));
                model.setGoodWeights(String.format("%.3f",goodWeight.doubleValue()));
            }else{
                model.setGoodsName("无货品信息");
                model.setUnit("-");
                model.setGoodsId("-");
                model.setGoodWeights("-");
            }
            return model;
        }).collect(Collectors.toList());

        try {
            OutputStream out = new FileOutputStream(xslPath+params.get("goodFileName"));
            beg=System.currentTimeMillis();
            EasyExcelUtil.writeExcelWithModel(out, models, AggreGoodXLSModel.class, ExcelTypeEnum.XLSX);
            log.info("write good file succ!! fileName:{},fileSize:{},cost:{} ms",xslPath+params.get("goodFileName"),models.size(),System.currentTimeMillis()-beg);
        }catch (Exception e){
            log.error("写入xls文件错误,错误信息:",e);
        }



    }

}
