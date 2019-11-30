package com.mallcai.fulfillment.biz.service.impl;

import com.mallcai.fulfillment.biz.rocketmq.provide.DcTransNoticeProducer;
import com.mallcai.fulfillment.biz.service.DcTransNoticeService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp.DispatchCommandReceiptMsg;
import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import com.mallcai.open.api.model.tms.TransportPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18 13:52:16
 */
@Slf4j
@Service
public class DcTransNoticeServiceImpl implements DcTransNoticeService {

    @Autowired
    private DcTransNoticeProducer dcTransNoticeProducer;

    @Override
    public void noticeTransLine(TransportPlan transportPlan) {

//        DispatchCommandReceiptMsg dispatchCommandReceiptMsg = new (CommandTypeEnum.);
//        dcTransNoticeProducer.send()
    }

    @Override
    public void noticeTransCar(TransportPlan transportPlan) {

//        dcTransNoticeProducer.se
    }


}
