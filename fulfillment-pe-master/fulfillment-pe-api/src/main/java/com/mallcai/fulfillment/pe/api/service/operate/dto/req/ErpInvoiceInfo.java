package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderInfoMsg;
import lombok.Data;

import java.util.Map;

/**
 * @description:  erp发货单信息
 * @author: chentao
 * @create: 2019-08-22 22:26:00
 */
@Data
public class ErpInvoiceInfo {

    private Map<OrderAggregationTypeEnum, Map<String, ProduceOrderInfoMsg>> divideInvoiceInfo;
}
