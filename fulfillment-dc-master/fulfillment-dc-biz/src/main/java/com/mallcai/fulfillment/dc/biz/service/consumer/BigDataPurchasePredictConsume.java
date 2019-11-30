package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchasePredictRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.BigDataPurchaseConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceivePurchaseForecastInnerService;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.utils.RedisDistributeLockUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 大数据T+2预测消息接收
 * @author Liu Yang
 * @date 2019/9/27 2:09 PM
 */
@Slf4j
@Component
public class BigDataPurchasePredictConsume extends AbstractListener {

    @Autowired
    private BigDataPurchaseConsumeConfigure configure;

    @Resource
    private ReceivePurchaseForecastInnerService receivePurchaseForecastInnerService;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    private final Long LOCK_EXPIRE_TIME = 10 * 60L;


    public BigDataPurchasePredictConsume() {
        super("City_StoreProduct_N");
    }

    private final String tag="forecast_purchase";

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("HDFSOrderPipelineReceiveConsume.consume receive order msg body:[{}]", body);

        PurchasePredictRequestDTO purchasePredictRequestDTO;
        try {
            purchasePredictRequestDTO = JSONObject.parseObject(body, PurchasePredictRequestDTO.class);
        } catch (Exception e) {
            log.error("HDFSOrderPipelineReceiveConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        boolean result=false;
        //分布式锁锁定
        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_PURCHASE_PREDICT_T_2+purchasePredictRequestDTO.getCityId()+"_"+
            purchasePredictRequestDTO.getWarehouseId()+"_"+purchasePredictRequestDTO.getCategoryType();
        boolean lockResult = false;
        try {
            log.info("采购预测命令接收开始,purchasePredictRequestDTO:{}",JSON.toJSONString(purchasePredictRequestDTO));
            lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);
            Byte forecastType=Constants.PREDICT_T_2;
            if (lockResult == false) {
                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return false;
            }
            log.info("采购预测命令接收开始,加锁成功,purchasePredictRequestDTO:{}",JSON.toJSONString(purchasePredictRequestDTO));
            result= receivePurchaseForecastInnerService.receiveMsg(purchasePredictRequestDTO,forecastType);
            log.info("执行采购预测导入结束,purchasePredictRequestDTO:{}", JSON.toJSONString(purchasePredictRequestDTO));
        } catch (Exception e) {
            log.error("执行采购预测导入异常", e);
            throw e;
        } finally {
            if (lockResult == true) {
                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
                log.info("采购预测命令接收结束,解锁成功,purchasePredictRequestDTO:{}",JSON.toJSONString(purchasePredictRequestDTO));
            }
        }


        return result;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), tag);
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("OrderPipelineReceiveConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
