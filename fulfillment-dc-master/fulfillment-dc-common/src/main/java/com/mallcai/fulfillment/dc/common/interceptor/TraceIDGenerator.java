package com.mallcai.fulfillment.dc.common.interceptor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 用以生成TraceID的ID生成器
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class TraceIDGenerator {

    /**
     * 时间戳+随机数
     **/
    public static String generate() {
        long currentTime = System.currentTimeMillis();
        long timeStamp = currentTime % 1000000L;
        int randomNumber = ThreadLocalRandom.current().nextInt(10000);
        long traceId = timeStamp * 10000 + randomNumber;
        return Long.toHexString(traceId).toUpperCase();
    }

}
