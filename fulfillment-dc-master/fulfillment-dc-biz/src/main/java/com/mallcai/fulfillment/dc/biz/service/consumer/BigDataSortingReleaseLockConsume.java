package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.AggregateOrderMessage;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.SortingRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.BigDataSortingConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.BigDataSortingReleaseLockConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveSortingForecastInnerService;
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

/** 大数据分拣消息接收
 * @author Liu Yang
 * @date 2019/9/27 2:09 PM
 */
@Slf4j
@Component
public class BigDataSortingReleaseLockConsume extends AbstractListener {

    @Autowired
    private BigDataSortingReleaseLockConsumeConfigure configure;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    private final Long LOCK_EXPIRE_TIME = 10 * 60L;


    public BigDataSortingReleaseLockConsume() {
        super(Constants.SORTING_RELEASE_LOCK_TOPIC);
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("BigDataSortingReleaseLockConsume.consume receive order msg body:[{}]", body);

        AggregateOrderMessage aggregateOrderMessage;
        try {
            aggregateOrderMessage = JSONObject.parseObject(body, AggregateOrderMessage.class);
        } catch (Exception e) {
            log.error("BigDataSortingReleaseLockConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        //分布式锁锁定
        String requestId = aggregateOrderMessage.getCityId()+"_"+aggregateOrderMessage.getWarehousesId();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_SORTING_PREDICT+aggregateOrderMessage.getCityId()+"_"+aggregateOrderMessage.getWarehousesId();
        boolean lockResult = false;
        try {
            log.info("预测分拣解锁命令接收开始,开始解锁,sortingRequestDTO:{}",JSON.toJSONString(aggregateOrderMessage));
            lockResult =  redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
        } catch (Exception e) {
            log.warn("执行分拣预测解锁异常", e);
            throw e;
        }
        String lockValue=null;
        try {
            lockValue = jedisClientUtil.get(lockKey);
        }catch (Exception e){
            log.warn("获取锁的值失败,lockKey:{},exception:{}", lockKey,e);
            throw e;
        }

        if (lockResult == false) {
            if(lockValue==null){
                log.warn("执行分拣预测解锁失败,锁已被释放,lockKey:{}", lockKey);
                lockResult=true;
            }else{
                log.warn("执行分拣预测解锁失败,lockKey:{}", lockKey);
            }
        }
        log.info("执行分拣预测解锁成功结束,sortingRequestDTO:{}", JSON.toJSONString(aggregateOrderMessage));

        return lockResult;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("BigDataSortingReleaseLockConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
