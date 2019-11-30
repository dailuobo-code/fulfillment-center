package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchasePredictRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.SortingRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.BigDataPurchaseConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.BigDataSortingConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceivePurchaseForecastInnerService;
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
public class BigDataSortingPredictConsume extends AbstractListener {

    @Autowired
    private BigDataSortingConsumeConfigure configure;

    @Resource
    private ReceiveSortingForecastInnerService receiveSortingForecastInnerService;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    private final Long LOCK_EXPIRE_TIME = 10 * 60L;


    public BigDataSortingPredictConsume() {
        super("City_StoreProduct_N");
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("BigDataSortingPredictConsume.consume receive order msg body:[{}]", body);

        SortingRequestDTO sortingRequestDTO;
        try {
            sortingRequestDTO = JSONObject.parseObject(body, SortingRequestDTO.class);
        } catch (Exception e) {
            log.error("BigDataSortingPredictConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        boolean result=false;
        //分布式锁锁定
        String requestId = sortingRequestDTO.getCityId()+"_"+sortingRequestDTO.getWarehouseId();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_SORTING_PREDICT+sortingRequestDTO.getCityId()+"_"+sortingRequestDTO.getWarehouseId();
        boolean lockResult = false;
        try {
            log.info("预测命令接收开始,开始加锁,sortingRequestDTO:{}",JSON.toJSONString(sortingRequestDTO));
            lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);
            if (lockResult == false) {
                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return false;
            }
            log.info("预测命令接收,加锁成功,sortingRequestDTO:{}",JSON.toJSONString(sortingRequestDTO));
            result= receiveSortingForecastInnerService.receiveMsg(sortingRequestDTO,lockKey,requestId);
            log.info("执行采购预测导入结束,sortingRequestDTO:{}", JSON.toJSONString(sortingRequestDTO));
        } catch (Exception e) {
            log.error("执行分拣预测导入异常", e);
            throw e;
        }
//        } finally {
//            if (lockResult == true) {
//                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
//                log.info("预测命令接收结束,解锁成功,sortingRequestDTO:{}",JSON.toJSONString(sortingRequestDTO));
//            }
//        }


        return result;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "forecast_correction_1");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("OrderPipelineReceiveConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
