package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.OrderRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.OrderConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveOrderInnerService;
import com.mallcai.trade.common.model.SOAOrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bh.zhong
 * @date 2019/8/20 2:09 PM
 */
@Slf4j
@Component
public class OrderPipelineReceiveConsume extends AbstractListener {

    @Autowired
    private OrderConsumeConfigure configure;

    @Resource
    private ReceiveOrderInnerService receiveOrderInnerService;

    public OrderPipelineReceiveConsume() {
        super("Trade_OrderStatusUpdate_N");
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("OrderPipelineReceiveConsume.consume receive order msg body:[{}]", body);

        SOAOrderDetailVO orderDetailVO;
        try {
            orderDetailVO = JSONObject.parseObject(body, SOAOrderDetailVO.class);
        } catch (Exception e) {
            log.error("OrderPipelineReceiveConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        if(orderDetailVO==null || orderDetailVO.getCityId() ==null || orderDetailVO.getOrderId() == null ){
            return true;
        }
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setCityId(orderDetailVO.getCityId());
        requestDTO.setOrderNo(orderDetailVO.getOrderId());
        requestDTO.setStoreId(orderDetailVO.getStoreId());
        return receiveOrderInnerService.receiveOrder(orderDetailVO);
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("OrderPipelineReceiveConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
