package com.mallcai.fulfillment.biz.reshandle;

import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.CityEnum;
import com.mallcai.fulfillment.biz.object.enums.OrderStatusEnum;
import com.mallcai.fulfillment.infrastructure.mapper.erp.OmsStandardOrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.pe.PeOrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderExtend;
import com.mallcai.fulfillment.infrastructure.object.pe.PeOrderExtend;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("FreshFulfillmentResultHandler")
@Slf4j
public class FreshFulfillmentResultHandler implements ResultHandler{
    @Resource
    private PeOrderMapperExtend peOrderMapperExtend;
    @Resource
    private OmsStandardOrderMapperExtend omsStandardOrderMapperExtend;

    @Override
    public void handleResult(CheckContext checkContext) {
        log.info("即将开始进行生鲜分拣总数校验...checkNo={}",checkContext.getCheckNo());
        checkTotalNum(checkContext);
        log.info("即将开始进行生鲜分拣出错数据反查...checkNo={}",checkContext.getCheckNo());
        List<Object> errorKeyList=checkContext.getErrorKeyList();
        if(CollectionUtils.isNotEmpty(errorKeyList)){
            StringBuffer errorKeyBuffer=new StringBuffer();
            errorKeyList.parallelStream().forEach(item->{errorKeyBuffer.append(String.valueOf(item)+",");});
            log.error("生鲜分拣，以下goodsId存在货品数量不一致问题:goodsIdList:{}",errorKeyBuffer.toString());
        }
        log.info("出错数据反查结束...checkNo={}",checkContext.getCheckNo());
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
            stringBuffer.append("\n对账内容:【履约、ERP生鲜分拣对账】\n【城市名称,城市ID,仓库ID,履约数据,ERP数据】\n");
            for(PeOrderExtend peOrderExtend:peOrderExtendList){
                stringBuffer.append(CityEnum.fromCityId(peOrderExtend.getCityId()).getCityName()+","+peOrderExtend.getCityId()+","+peOrderExtend.getWarehouseId()+","+peOrderExtend.getCalCount()+",");
                boolean find=false;
                for(OmsStandardOrderExtend omsStandardOrderExtend:omsStandardOrderExtendList){
                    if(omsStandardOrderExtend.getCityId().equals(peOrderExtend.getCityId())&&omsStandardOrderExtend.getWarehouseId().equals(peOrderExtend.getWarehouseId())){
                        stringBuffer.append(omsStandardOrderExtend.getCalCount()+"\n");
                        find=true;
                        if(!omsStandardOrderExtend.getCalCount().equals(peOrderExtend.getCalCount())){
                            checkResult=false;
                        }
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


}
