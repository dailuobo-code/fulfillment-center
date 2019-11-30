package com.mallcai.fulfillment.dp.biz.service.impl.task;

import com.mallcai.ddframe.job.lite.spring.annotation.ElasticScheduler;
import com.mallcai.fulfillment.common.utils.DateTimeUtils;
import com.mallcai.fulfillment.dp.biz.service.impl.inner.DeliveryOrderInnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 确认收货任务
 * @author bh.zhong
 * @date 2019/9/9 9:56 PM
 */
@Slf4j
@Service
public class ConfirmDeliveryTask {

    @Resource
    private DeliveryOrderInnerService deliveryOrderInnerService;

    @ElasticScheduler(cron = "0 0 0 1/1 * ?")
    public void executeConfirmDelivery() {
        try {
            log.info("执行确认收货任务开始");
            deliveryOrderInnerService.batchConfirmOrder(DateTimeUtils.getPrevDate(DateTimeUtils.formatDateStart(new Date()),7));
            log.info("执行确认收货任务结束");
        } catch (Exception e) {
            log.error("执行确认收货异常",e);
        }
    }


}
