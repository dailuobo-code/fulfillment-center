package com.mallcai.fulfillment.pe.biz.service.gray;

import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.deploy.test.BaseTestCase;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * @description:
 * @author: chentao
 * @create: 2019-10-19 18:09:49
 */
public class SwitchServiceTest extends BaseTestCase {

    @Autowired
    private SwitchService switchService;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Test
    public void testISDistributedAggregateOpened(){

        Set<String> idStrs  =jedisClientUtil.smembers("123");
        System.out.println(CollectionUtils.isEmpty(idStrs));

//        boolean a = switchService.isDistributedAggregateOpened();

    }
}
