package com.mallcai.fulfillment.pe.biz.service.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.api.msg.OrderItemInfoListMsg;
import com.mallcai.fulfillment.pe.biz.service.inner.sorting.ReceiveSortingForecastInnerService;
import com.mallcai.fulfillment.pe.biz.service.mq.consumer.config.HdfsSortingConsumeConfigure;
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
public class HdfsSortingPredictConsume extends AbstractListenerWithMsg {

    @Autowired
    private HdfsSortingConsumeConfigure configure;

    @Resource
    private ReceiveSortingForecastInnerService receiveSoringForecastInnerService;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    private final Long LOCK_EXPIRE_TIME = 10 * 60L;


    public HdfsSortingPredictConsume() {
        super(Constants.PE_SORTING_FORECAST_TOPIC);
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body, MessageExt msg) {
        log.info("HdfsSortingPredictConsume.consume receive order msg body:[{}]", body);

        OrderItemInfoListMsg orderItemInfoListMsg;
        try {
            orderItemInfoListMsg = JSONObject.parseObject(body, OrderItemInfoListMsg.class);
        } catch (Exception e) {
            log.error("HdfsSortingPredictConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        boolean result=false;
        //分布式锁锁定
        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_PURCHASE_PREDICT+"_"+orderItemInfoListMsg.getCityId()+"_"+
            orderItemInfoListMsg.getWarehouseId()+"_"+orderItemInfoListMsg.getForecastDate()+"_"+msg.getMsgId();
        boolean lockResult = false;
        try {
            lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);
            if (lockResult == false) {
                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return false;
            }
            result= receiveSoringForecastInnerService.receiveMsg(orderItemInfoListMsg);
            log.info("执行分拣预测导入结束,purchasePredictRequestDTO:{}", JSON.toJSONString(orderItemInfoListMsg));
        } catch (Exception e) {
            log.error("执行分拣预测导入异常", e);
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
