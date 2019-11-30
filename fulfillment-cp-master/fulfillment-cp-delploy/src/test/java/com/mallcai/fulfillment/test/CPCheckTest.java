package com.mallcai.fulfillment.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.mallcai.fulfillment.biz.mallcaijob.ErpWmsCheckTask;
import com.mallcai.fulfillment.biz.mallcaijob.FulErpFreshSortingCheckTask;
import com.mallcai.fulfillment.biz.mallcaijob.FulfillmentCheckTask;
import com.mallcai.fulfillment.biz.object.bo.CheckConfigBo;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.AsyncPayStatus;
import com.mallcai.fulfillment.biz.object.enums.CityEnum;
import com.mallcai.fulfillment.biz.object.enums.OrderStatusEnum;
import com.mallcai.fulfillment.biz.service.ErpWmsLoaderService;
import com.mallcai.fulfillment.deploy.Application;
import com.mallcai.fulfillment.infrastructure.mapper.erp.OmsStandardOrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.pe.PeOrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.trade.OrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderExtend;
import com.mallcai.fulfillment.infrastructure.object.pe.PeOrderExtend;
import com.mallcai.manager.common.constant.Constants;
import com.mallcai.manager.common.util.DateUtil;
import com.mallcai.service.order.constants.DeliveryModeEnum;
import com.mallcai.service.order.constants.OrderTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.mallcai.service.order.constants.OrderItemProductInfoKeys.IS_FROZEN;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class CPCheckTest {

    @Autowired
    private FulfillmentCheckTask fulfillmentCheckTask;
    @Autowired
    private FulErpFreshSortingCheckTask fulErpFreshSortingCheckTask;
    @Resource
    private OrderMapperExtend orderMapperExtend;

    @Resource
    private PeOrderMapperExtend peOrderMapperExtend;
    @Resource
    private OmsStandardOrderMapperExtend omsStandardOrderMapperExtend;

    @Autowired
    private ErpWmsCheckTask erpWmsCheckTask;

    @Test
    public void fromCityId(){
        Integer cityId=27;
        String cityName= CityEnum.fromCityId(cityId).getCityName();
        System.out.println("");
    }

    @Test
    public void testTable(){
        CheckContext checkContext=new CheckContext();
        CheckConfigBo config = CheckConfigBo.builder()
                .bizTag("FRESH_SORTING_COMPARE")
                .bizParam(ImmutableMap.of("cities", "30","groupValue","3")).build();
        fulErpFreshSortingCheckTask.execute(JSON.toJSONString(config));
//        checkContext.setBeginTime(DateUtil.todayStart());
//        checkContext.setEndTime(DateUtil.addDays(checkContext.getBeginTime(),1));
//        checkContext.setCheckNo("test001");
//        checkContext.setBizParam(config.getBizParam());
//        checkTotalNum(checkContext);
    }

    public void checkTotalNum(CheckContext checkContext){
        String cities=(String)checkContext.getBizParam().get(Constants.PARAM_CITY);
        String groupValue=(String)checkContext.getBizParam().get(Constants.PARAM_GROUP_VALUE);
        List<Integer> cityIdList= StringUtils.isEmpty(cities)?null:(StringUtils.equals(cities,Constants.ALL_CITY)?null: Arrays.asList(cities.split(",")).stream().map(w->Integer.valueOf(w)).collect(Collectors.toList()));
        List<PeOrderExtend> peOrderExtendList=peOrderMapperExtend.calFreshTotalNumByCondition(checkContext.getBeginTime(),groupValue,cityIdList, OrderStatusEnum.PUSH_COMPLETE.getStatus());
        List<OmsStandardOrderExtend> omsStandardOrderExtendList=omsStandardOrderMapperExtend.calFreshSortingTotalNumByCondition(checkContext.getBeginTime(),checkContext.getEndTime(),cityIdList,Integer.valueOf(groupValue));
        boolean checkResult=true;
        StringBuffer stringBuffer=new StringBuffer("供应链全链路对账");
        if(CollectionUtils.isNotEmpty(peOrderExtendList)){
            if(CollectionUtils.isEmpty(omsStandardOrderExtendList)){
                stringBuffer.append("\n检测到ERP侧生鲜数据尚未落库,无法对账,本次对账凭证ID:").append(checkContext.getCheckNo());
                log.warn(stringBuffer.toString());
                return;
            }
            stringBuffer.append("\n对账内容:【履约、ERP生鲜分拣对账】\n【城市,仓库,履约,ERP】\n");
            for(PeOrderExtend peOrderExtend:peOrderExtendList){
                stringBuffer.append(peOrderExtend.getCityId()+","+peOrderExtend.getWarehouseId()+","+peOrderExtend.getCalCount()+",");
                boolean find=false;
                for(OmsStandardOrderExtend omsStandardOrderExtend:omsStandardOrderExtendList){
                    if(omsStandardOrderExtend.getCityId().equals(peOrderExtend.getCityId())&&omsStandardOrderExtend.getWarehouseId().equals(peOrderExtend.getWarehouseId())){
                        stringBuffer.append(omsStandardOrderExtend.getCalCount()+"\n");
                        find=true;
                    }
                }
                if(!find){
                    stringBuffer.append("0\n");
                    checkResult=false;
                }
            }
        }else{
            stringBuffer.append("\n检测到履约侧生鲜数据尚未落库,无法对账,本次对账凭证ID:").append(checkContext.getCheckNo());
            log.warn(stringBuffer.toString());
            return;
        }

        if (checkResult) {
            stringBuffer.append("\n对账结果: 一致 ,本次对账凭证ID:").append(checkContext.getCheckNo());
        } else {
            stringBuffer.append("\n对账结果: 不一致 ,本次对账凭证ID:").append(checkContext.getCheckNo());
        }
        log.warn(stringBuffer.toString());
    }


    @Test
    public void testFrozen(){
        CheckConfigBo config = CheckConfigBo.builder()
                .bizTag("FRESH_SORTING_COMPARE")
                .bizParam(ImmutableMap.of("cities", "30")).build();
        System.out.println("============================="+ JSON.toJSONString(config)+"============");
        fulErpFreshSortingCheckTask.execute(JSON.toJSONString(config));
    }


    @Test
    public void testInvode(){
        CheckConfigBo config = CheckConfigBo.builder()
                .bizTag("TRADE_COMPARE")
                .bizParam(ImmutableMap.of("cities", "30",
                                         "interval", 1800))
                .build();
        System.out.println("============================="+ JSON.toJSONString(config)+"============");
        fulfillmentCheckTask.execute(JSON.toJSONString(config));
    }

    @Test
    public void testCount(){
        Date endTime=new Date();
        Date beginTime= DateUtil.addSeconds(endTime,-3600*5);
        List<Byte> orderTypeList=new ArrayList<>();
        orderTypeList.add((byte) OrderTypeEnum.PRESELL.getValue());
        orderTypeList.add((byte)OrderTypeEnum.STANDARD_PRODUCT.getValue());
        String cities="30";
        List<Integer> cityIdList= StringUtils.isEmpty(cities)?null: Arrays.asList(cities.split(",")).stream().map(w->Integer.valueOf(w)).collect(Collectors.toList());
//        Integer status= OrderStatusEnum.PAID.getValue();
        String asyncPayStatus= AsyncPayStatus.FAIL.getValue();
        int count=orderMapperExtend.countByCondition(beginTime,endTime,(byte) DeliveryModeEnum.PICK_UP.getValue(),orderTypeList,"%"+IS_FROZEN+"%",cityIdList,2,asyncPayStatus);
        System.out.println(count);
    }

    @Test
    public void testErpWms(){
        CheckConfigBo config = CheckConfigBo.builder()
                .bizTag("ERP_WMS_FROZEN_COMPARE")
                .bizParam(ImmutableMap.of("cities", "30",
                        "orderType", "2"))
                .build();
        System.out.println("============================="+ JSON.toJSONString(config)+"============");
        erpWmsCheckTask.execute(JSON.toJSONString(config));

        CheckConfigBo config1 = CheckConfigBo.builder()
                .bizTag("ERP_WMS_FRESH_COMPARE")
                .bizParam(ImmutableMap.of("cities", "30",
                        "orderType", "3"))
                .build();
        System.out.println("============================="+ JSON.toJSONString(config1)+"============");
        erpWmsCheckTask.execute(JSON.toJSONString(config1));
    }

}
