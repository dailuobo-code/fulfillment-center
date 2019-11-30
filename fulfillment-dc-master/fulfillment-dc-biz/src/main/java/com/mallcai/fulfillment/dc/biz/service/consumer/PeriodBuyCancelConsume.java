package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.OrderRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.consumer.AbstractListener;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.OrderConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.impl.operate.PeriodBuyService;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveOrderInnerService;
import com.mallcai.trade.business.api.aftersales.enums.AfterSalesOuterStatusEnum;
import com.mallcai.trade.business.api.aftersales.msg.AfterSalesStatusChangingDTO;
import com.mallcai.trade.common.model.SOAOrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class PeriodBuyCancelConsume extends AbstractListener {

    @Value("${rocketmq.consumer.fulfillment.period.groupName:Fulfillment_PeriodBuy}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr:}")
    private String namesrvAddr;

    private boolean isOrderly;

    @Resource
    private PeriodBuyService periodBuyService;

    public PeriodBuyCancelConsume() {
        super("Trade_afterSales_status_changing_N");
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("PeriodBuyCancelConsume.consume receive msg body:[{}]", body);

        AfterSalesStatusChangingDTO dto = JSON.parseObject(body, AfterSalesStatusChangingDTO.class);

        if(!ObjectUtils.equals(AfterSalesOuterStatusEnum.DONE.getStatus(),dto.getStatus())){
            log.info("收到周期购消息,status:{},直接过滤!",dto.getStatus());
            return true;
        }
        try {
            periodBuyService.CancelPeroidBuy(dto);
        }catch (Exception e){
            log.error("周期购订单[{}] 退订错误!!",dto.getOrderId(),e);
        }

        return true;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, groupName, namesrvAddr, isOrderly);
        } catch (MQClientException e) {
            log.error("OrderPipelineReceiveConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
