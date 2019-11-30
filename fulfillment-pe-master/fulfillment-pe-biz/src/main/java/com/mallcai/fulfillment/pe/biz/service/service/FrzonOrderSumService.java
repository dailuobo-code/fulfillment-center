package com.mallcai.fulfillment.pe.biz.service.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.biz.service.bo.TransDbDataBo;
import com.mallcai.fulfillment.pe.biz.service.util.BaseBeanUtil;
import com.mallcai.fulfillment.pe.infrastructure.mapper.FrozenGoodSumPoMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.FrozenGoodSumPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FrzonOrderSumService {
    @ConfigValue(key="/app_fulfillment/common/tms.data.config")
    private Map<String,Object> tmsConfig;

    @Qualifier("fpeNamedTemplate")
    @Autowired
    NamedParameterJdbcTemplate fpeNamedTemplate;

    @Autowired
    FrozenGoodSumPoMapperExtend frozenGoodSumPoMapperExtend;



    public void sumRecord(){

        //删除所有记录
        int deleteSum = fpeNamedTemplate.update("truncate table frozen_good_summary", new HashMap<>());
        log.info("删除 frozen_good_summary 记录数: cnt:{}",deleteSum);

        String sumSql="select city_id,store_id,city_product_id,sum(SUBSTR(commodity_info_ext,INSTR(commodity_info_ext,'productNum\":')+12,INSTR(commodity_info_ext,',\"packageQuantity')-INSTR(commodity_info_ext,'productNum\":')-12)) as product_num,commodity_info_ext from pe_order \n" +
                "where expect_push_time = :pushTime\n" +
                "and create_time between :createTime and  :endTime \n" +
                "and group_value='2' and city_id in (:cities) \n" +
                "and status not in (-1,5) \n" +
                "group by store_id,city_product_id";

        // 按照城市生成所有记录
        List<Integer> cities = (List<Integer>) tmsConfig.get("cities");
        Integer day=tmsConfig.containsKey("frozenDayCnt")?(Integer) tmsConfig.get("frozenDayCnt"):30;
        String begTime=tmsConfig.containsKey("frozenBeg")?(String)tmsConfig.get("frozenBeg"):"16:30:00";
        String endTime=tmsConfig.containsKey("frozenEnd")?(String)tmsConfig.get("frozenEnd"):"20:00:00";


        Map<String,Object> params= Maps.newHashMap();

        params.put("cities",cities);


        for (int i = 0; i < day; i++) {
            long beg=System.currentTimeMillis();
            Date pushTime=DateUtils.addDay(DateUtils.startDate(new Date()),-i-1);
            Date periodBeg = DateUtils.parseDate(DateUtils.formatDate(pushTime) + " "+begTime, DateUtils.COMPLETE_DATE_PATTERN);
            Date periodEnd = DateUtils.parseDate(DateUtils.formatDate(pushTime) + " "+endTime, DateUtils.COMPLETE_DATE_PATTERN);
            params.put("createTime",periodBeg);
            params.put("endTime",periodEnd);
            params.put("pushTime",pushTime);

            List<TransDbDataBo> dbList = fpeNamedTemplate.query(sumSql, params, new RowMapper<TransDbDataBo>() {
                @Override
                public TransDbDataBo mapRow(ResultSet resultSet, int i) throws SQLException {
                    return TransDbDataBo.builder()
                            .cityId(resultSet.getInt("city_id"))
                            .storeId(resultSet.getInt("store_id"))
                            .cityProductId(resultSet.getInt("city_product_id"))
                            .productNum(resultSet.getBigDecimal("product_num"))
                            .prodInfo(resultSet.getString("commodity_info_ext"))
                            .build();
                }
            });

            List<FrozenGoodSumPo> goosSum = dbList.stream().map(p -> {
                FrozenGoodSumPo po = BaseBeanUtil.convertObject(p, FrozenGoodSumPo.class);
                JSONObject prodInfo = JSON.parseObject(p.getProdInfo());
                po.setGoodId(Integer.valueOf(prodInfo.getString("goodsId")));
                po.setGoodSum(p.getProductNum().multiply(prodInfo.getBigDecimal("relNum")));
                po.setSumDate(DateUtils.formatDate(pushTime));
                po.setProductSum(p.getProductNum());
                po.setCreateTime(new Date());
                return po;
            }).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(goosSum)) {
                frozenGoodSumPoMapperExtend.batchInsertSelective(goosSum);
                log.info("{} 数据集成完成,集成数据条数:{},耗时:{}ms", DateUtils.formatDate(pushTime), goosSum.size(), System.currentTimeMillis() - beg);
            }
        }


    }

}
