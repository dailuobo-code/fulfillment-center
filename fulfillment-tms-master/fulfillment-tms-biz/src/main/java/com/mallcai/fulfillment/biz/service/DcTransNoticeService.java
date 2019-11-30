package com.mallcai.fulfillment.biz.service;

import com.mallcai.open.api.model.tms.TransportPlan;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18 13:48:56
 */
public interface DcTransNoticeService {

    void noticeTransLine(TransportPlan transportPlan);

    void noticeTransCar(TransportPlan transportPlan);
}
