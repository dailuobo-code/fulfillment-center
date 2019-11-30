package com.mallcai.fulfillment.biz.service;

import com.mallcai.backend.common.utils.DateUtil;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.fulfillment.biz.util.BaseBeanUtil;
import com.mallcai.fulfillment.infrastructure.mapper.tms.DeliveryRecordPoMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.tms.TransportLinePoMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.tms.TransportPlanPoMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportLinePo;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportPlanPo;
import com.mallcai.open.api.model.tms.Line;
import com.mallcai.open.api.model.tms.TransportPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class TransLineService {

    @Autowired
    TransportPlanPoMapperExtend transportPlanPoMapperExtend;

    @Autowired
    TransportLinePoMapperExtend transportLinePoMapperExtend;

    @Autowired
    DeliveryRecordPoMapperExtend deliveryRecordPoMapperExtend;



    public void saveTransPlan(TransportPlan plan){


        String planNo = plan.getCustomerPlanNO();

        TransportPlanPo transportPlanPo = transportPlanPoMapperExtend.selectByPlanNo(planNo);

        if(transportPlanPo==null) {

            TransportPlanPo transPlan = BaseBeanUtil.convertObject(plan, TransportPlanPo.class);
            transPlan.setPlanNo(plan.getCustomerPlanNO());
            transPlan.setSendCar(DateUtils.parseDate(plan.getSendCarTime(), DateUtils.COMPLETE_DATE_PATTERN));
            transportPlanPoMapperExtend.insertSelective(transPlan);


            List<TransportLinePo> linePOs = plan.getLineList().stream().map(p -> {
                TransportLinePo line = BaseBeanUtil.convertObject(p, TransportLinePo.class);
                line.setOrderNo(p.getOrderNo());
                line.setPlanNo(plan.getCustomerPlanNO());
                line.setMinArrive(DateUtils.parseDate(p.getMinArriveTime(), DateUtils.COMPLETE_DATE_PATTERN));
                line.setMaxArrive(DateUtils.parseDate(p.getMaxArriveTime(), DateUtils.COMPLETE_DATE_PATTERN));
                line.setMinLeave(DateUtils.parseDate(p.getMinLeaveTime(), DateUtils.COMPLETE_DATE_PATTERN));
                line.setMaxLeave(DateUtils.parseDate(p.getMaxLeaveTime(), DateUtils.COMPLETE_DATE_PATTERN));
                line.setRealArrive(DateUtils.parseDate(p.getRealArriveTime(), DateUtils.COMPLETE_DATE_PATTERN));
                line.setRealLeave(DateUtils.parseDate(p.getRealLeaveTime(), DateUtils.COMPLETE_DATE_PATTERN));
                line.setDeliveryAmt(0);
                line.setDelivery(false);
                return line;
            }).collect(Collectors.toList());

            try {
                transportLinePoMapperExtend.batchInsertSelective(linePOs);
            }catch (Exception e){
                log.error("插入线路信息表 trans_line 错误!",e);
            }


            List<String> waves = plan.getLineList().stream().map(Line::getOrderNo).collect(Collectors.toList());

            deliveryRecordPoMapperExtend.updateTransPlanByWaveNo(new Date(),waves);

        }


        //排线的结构体重可能出现排车
        if(transportPlanPo!=null) {
            BaseBeanUtil.copyProperties(plan,transportPlanPo);
            transportPlanPoMapperExtend.updateByPrimaryKeySelective(transportPlanPo);
            List<String> waves = plan.getLineList().stream().map(Line::getOrderNo).collect(Collectors.toList());
            deliveryRecordPoMapperExtend.updateTransCarByWaveNo(new Date(),waves);
        }






    }
}
