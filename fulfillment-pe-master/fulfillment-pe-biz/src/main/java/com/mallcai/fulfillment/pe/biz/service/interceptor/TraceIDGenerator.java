package com.mallcai.fulfillment.pe.biz.service.interceptor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 用以生成TraceID的ID生成器
 * @author zhanghao
 * @date 2019-08-14 00:20:00
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
