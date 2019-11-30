package com.mallcai.fulfillment.pe.biz.service.inner.transorder.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;
import com.mallcai.fulfillment.pe.biz.service.inner.transorder.TransOrderInfoHandleService;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.service.order.constants.OrderExtraKeys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @description: 交易订单信息处理实现类
 * @author: chentao
 * @create: 2019-09-23 16:44:05
 */
@Slf4j
@Service
public class TransOrderInfoHandleServiceImpl implements TransOrderInfoHandleService {

    /**
     * 社区团购订单标识
     */
    private final static String ASSOCIATION_GROUPER_FLAG = "1";

    @Override
    public boolean isGroupBuyingOrder(OrderAcceptRequestDTO transOrder) {

        log.info("判断订单是否是社区团购订单,extras:{}", transOrder.getExtras());
        AssertUtils.assertNotBlank(transOrder.getExtras());

        boolean isGroupBuyingOrder = false;
        JSONObject extraJson = JSON.parseObject(transOrder.getExtras());

        String associationGrouperInfo = extraJson.getString(OrderExtraKeys.IS_ASSOCIATION_GROUPER_ORDER);
        if (StringUtils.isNotEmpty(associationGrouperInfo) && ASSOCIATION_GROUPER_FLAG.equals(associationGrouperInfo)){

            isGroupBuyingOrder = true;
        }

        log.info("判断订单是否是社区团购订单,extras:{},结果:{}", transOrder.getExtras(), isGroupBuyingOrder);

        return isGroupBuyingOrder;
    }
}
