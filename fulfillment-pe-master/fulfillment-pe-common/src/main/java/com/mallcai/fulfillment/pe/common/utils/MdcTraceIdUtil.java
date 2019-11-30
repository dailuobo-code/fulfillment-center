package com.mallcai.fulfillment.pe.common.utils;

import com.mallcai.fulfillment.pe.common.constants.Constants;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @description: mdc traceId
 * @author: chentao
 * @create: 2019-10-31 15:13:59
 */
public class MdcTraceIdUtil {

    public static void addTraceId(){

        MDC.put(Constants.MDC_TRACE_ID, UUID.randomUUID().toString());
    }
}
