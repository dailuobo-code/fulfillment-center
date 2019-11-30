package com.mallcai.fulfillment.pe.biz.service.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO;
import com.mallcai.fulfillment.pe.biz.service.inner.purchase.ReceivePurchaseForecastInnerService;
import com.mallcai.fulfillment.pe.biz.service.mq.consumer.config.HdfsPurchaseConsumeConfigure;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 大数据T+2预测消息接收
 * @author Liu Yang
 * @date 2019/9/27 2:09 PM
 */
@Slf4j
@Component
public class HdfsPurchasePredictConsume extends AbstractListenerWithMsg {

    @Autowired
    private HdfsPurchaseConsumeConfigure configure;

    @Resource
    private ReceivePurchaseForecastInnerService receivePurchaseForecastInnerService;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    private final Long LOCK_EXPIRE_TIME = 10 * 60L;


    public HdfsPurchasePredictConsume() {
        super(Constants.PE_PURCHASE_FORECAST_TOPIC);
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body, MessageExt msg) {
        log.info("HdfsPurchasePredictConsume.consume receive order msg body:[{}]", body);

        PurchaseForecastInfoListDTO purchaseForecastInfoListDTO;
        try {
            purchaseForecastInfoListDTO = JSONObject.parseObject(body, PurchaseForecastInfoListDTO.class);
        } catch (Exception e) {
            log.error("HdfsPurchasePredictConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        boolean result=false;
        //分布式锁锁定

        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_PURCHASE_PREDICT+"_"+purchaseForecastInfoListDTO.getCityId()+
            "_"+purchaseForecastInfoListDTO.getWarehouseId()+"_"+purchaseForecastInfoListDTO.getForecastDate()+"_"+
            purchaseForecastInfoListDTO.getForecastType()+"_"+purchaseForecastInfoListDTO.getCategoryType()+"_"+msg.getMsgId();
        boolean lockResult = false;
        try {
            lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);
            if (lockResult == false) {
                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return false;
            }
            result= receivePurchaseForecastInnerService.receiveMsg(purchaseForecastInfoListDTO);
            log.info("执行采购预测导入结束,purchasePredictRequestDTO:{}", JSON.toJSONString(purchaseForecastInfoListDTO));
        } catch (Exception e) {
            log.error("执行采购预测导入异常", e);
            throw e;
        } finally {
            if (lockResult == true) {
                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
            }
        }


        return result;
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
