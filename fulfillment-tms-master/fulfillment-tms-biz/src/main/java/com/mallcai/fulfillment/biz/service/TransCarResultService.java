package com.mallcai.fulfillment.biz.service;

import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.backend.common.utils.StringUtils;
import com.mallcai.fulfillment.biz.util.BaseBeanUtil;
import com.mallcai.fulfillment.infrastructure.mapper.tms.DeliveryRecordPoMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.tms.TransportLinePoMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.tms.TransportPlanPoMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportLinePo;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportPlanPo;
import com.mallcai.open.api.model.tms.Line;
import com.mallcai.open.api.model.tms.Result;
import com.mallcai.open.api.model.tms.TransportPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransCarResultService {


    @Autowired
    TransportPlanPoMapperExtend transportPlanPoMapperExtend;

    @Autowired
    TransportLinePoMapperExtend transportLinePoMapperExtend;

    @Autowired
    DeliveryRecordPoMapperExtend deliveryRecordPoMapperExtend;


    public void updateTransCarResult(TransportPlan plan){

        // 根据结果更新line 表
        for (Result result : plan.getResultList()) {

            TransportLinePo linePo= BaseBeanUtil.convertObject(result,TransportLinePo.class);
            linePo.setRealArrive(DateUtils.parseDate(result.getRealArriveStoreTime(), DateUtils.COMPLETE_DATE_PATTERN));
            linePo.setRealLeave(DateUtils.parseDate(result.getRealLeaveStoreTime(), DateUtils.COMPLETE_DATE_PATTERN));
            linePo.setDeliveryAmt(result.getDeliveryAmt());
            linePo.setDelivery(result.isDelivery());
            linePo.setPlanNo(plan.getCustomerPlanNO());
            transportLinePoMapperExtend.updateByPlanNoAndOrgCode(linePo);
        }

        //更新整个计划表
        TransportPlanPo transportPlanPo = transportPlanPoMapperExtend.selectByPlanNo(plan.getCustomerPlanNO());
        if(transportPlanPo!=null){
            transportPlanPo.setComplete(plan.isComplete());
            if(StringUtils.isEmpty(plan.getCompleteTime())){
                transportPlanPo.setCompleteTime(new Date());
            }else {
                transportPlanPo.setCompleteTime(DateUtils.parseDate(plan.getCompleteTime(), DateUtils.COMPLETE_DATE_PATTERN));
            }
            transportPlanPo.setUpdateTime(new Date());
            transportPlanPo.setUpdateBy("TMS结果回调");
            transportPlanPoMapperExtend.updateByPrimaryKeySelective(transportPlanPo);
        }

        List<String> waves = plan.getResultList().stream().map(Result::getOrderNo).collect(Collectors.toList());
        deliveryRecordPoMapperExtend.updateTransResByWaveNo(new Date(),waves);


    }
}
