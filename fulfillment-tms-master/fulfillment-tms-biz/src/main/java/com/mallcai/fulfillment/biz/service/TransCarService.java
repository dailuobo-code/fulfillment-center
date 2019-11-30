package com.mallcai.fulfillment.biz.service;

import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.fulfillment.biz.util.BaseBeanUtil;
import com.mallcai.fulfillment.infrastructure.mapper.tms.DeliveryRecordPoMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.tms.TransportPlanPoMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportPlanPo;
import com.mallcai.open.api.model.tms.Line;
import com.mallcai.open.api.model.tms.TransportPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransCarService {

    @Autowired
    TransportPlanPoMapperExtend transportPlanPoMapperExtend;

    @Autowired
    DeliveryRecordPoMapperExtend deliveryRecordPoMapperExtend;

    // 排车计划
    public void updateTransCar(TransportPlan plan){

        TransportPlanPo transportPlanPo = transportPlanPoMapperExtend.selectByPlanNo(plan.getCustomerPlanNO());
        if(transportPlanPo!=null) {
            BaseBeanUtil.copyProperties(plan, transportPlanPo);
            transportPlanPo.setVehicleNo(plan.getVehicleNO());
            transportPlanPo.setDriverName(plan.getDriverName());
            transportPlanPo.setDriverPhone(plan.getDriverPhone());
            transportPlanPo.setSendCar(DateUtils.parseDate(plan.getSendCarTime(), DateUtils.COMPLETE_DATE_PATTERN));
            transportPlanPoMapperExtend.updateByPrimaryKeySelective(transportPlanPo);
        }

        List<String> waves = plan.getLineList().stream().map(Line::getOrderNo).collect(Collectors.toList());
        deliveryRecordPoMapperExtend.updateTransCarByWaveNo(new Date(),waves);
    }
}
