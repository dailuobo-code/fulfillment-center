package com.mallcai.manager.common.util;

import com.mallcai.manager.common.constant.Constants;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @description: mdc traceId
 * @author: liuyang
 * @create: 2019-10-31 15:13:59
 */
public class MdcTraceIdUtil {

    public static void addTraceId(){

        MDC.put(Constants.MDC_TRACE_ID, UUID.randomUUID().toString());
    }
}
