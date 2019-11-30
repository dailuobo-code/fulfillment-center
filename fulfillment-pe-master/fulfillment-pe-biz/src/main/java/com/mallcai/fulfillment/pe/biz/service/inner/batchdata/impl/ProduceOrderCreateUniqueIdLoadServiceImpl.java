package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.UniqueIdLoadService;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.enums.BatchDataTaskParamKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 生产单创建uniqueId获取
 * @author: chentao
 * @create: 2019-10-22 15:45:42
 */
@Slf4j
@Service
public class ProduceOrderCreateUniqueIdLoadServiceImpl implements UniqueIdLoadService {

    /**
     * 一次性从所有生产key集合中获取生产单key的个数
     */
    private final Integer ONCE_GET_PRODUCE_KEY_COUNT = 10000;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Override
    public BatchDataTypeEnum getHandleBatchDataType() {
        return BatchDataTypeEnum.CREATE_PRODUCE_ORDER;
    }

    @Override
    public List<Object> getUniqueIds(BatchDataTaskMessage batchDataTaskMessage) {

        String allProduceOrderMapKey = JSONObject.parseObject(batchDataTaskMessage.getParam()).getString(BatchDataTaskParamKeyEnum.ALL_PRODUCE_ORDER_KEY.getKey());

        log.info("获取所有生产单集合key开始，map的key:{}", allProduceOrderMapKey);
        List<Map.Entry<String, String>> allProduceOrderKey2SizeEntryList = iteratorAllProduceOrderKeyEntry(allProduceOrderMapKey);
        List<Object> produceOrderKeyEntryUniqueIds = allProduceOrderKey2SizeEntryList.stream().map(entry -> (Object) entry).collect(Collectors.toList());
        log.info("获取所有生产单集合key结束，map的key:{},结果:{}", allProduceOrderMapKey, JSON.toJSONString(allProduceOrderKey2SizeEntryList));

        return produceOrderKeyEntryUniqueIds;
    }

    private List<Map.Entry<String, String>> iteratorAllProduceOrderKeyEntry(String key) {

        List<Map.Entry<String, String>> allProduceOrderKeyEntryList = new ArrayList<>();

        // 游标初始值为0
        String cursor = ScanParams.SCAN_POINTER_START;
        ScanParams scanParams = new ScanParams();
        scanParams.count(ONCE_GET_PRODUCE_KEY_COUNT);
        while (true) {
            //使用hscan命令获取ONCE_GET_PRODUCE_KEY_COUNT条数据，使用cursor游标记录位置，下次循环使用
            ScanResult<Map.Entry<String, String>> hscanResult =
                    jedisClientUtil.hscan(key, cursor, scanParams);

            cursor = hscanResult.getStringCursor();

            log.info("迭代获取生产单key开始，当前游标:{}", cursor);

            List<Map.Entry<String, String>> scanResult =
                    hscanResult.getResult();
            log.info("迭代获取生产单key结束，结果:{}", JSON.toJSONString(scanResult));
            allProduceOrderKeyEntryList.addAll(scanResult);
            log.info("当前所有生产单key:{}", JSON.toJSONString(allProduceOrderKeyEntryList));
            // 返回0 说明遍历完成
            if ("0".equals(cursor)) {
                break;
            }
        }

        return allProduceOrderKeyEntryList;
    }
}
