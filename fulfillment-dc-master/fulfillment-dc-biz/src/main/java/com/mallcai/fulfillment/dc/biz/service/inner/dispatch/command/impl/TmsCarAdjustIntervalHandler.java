package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command.impl;

import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.IntervalHandler;
import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18
 */
@Slf4j
@Service
public class TmsCarAdjustIntervalHandler implements IntervalHandler {
    @Override
    public Date getExecuteTime() {

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int currHour = calendar.get(Calendar.HOUR_OF_DAY);

        // 当前时间小于20点，就为20点，否则延迟10分钟
        if (currHour < 20) {

            calendar.set(Calendar.HOUR_OF_DAY, 20);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            return calendar.getTime();
        } else {

            return DateUtil.addMinutes(now, 10);
        }
    }
}
