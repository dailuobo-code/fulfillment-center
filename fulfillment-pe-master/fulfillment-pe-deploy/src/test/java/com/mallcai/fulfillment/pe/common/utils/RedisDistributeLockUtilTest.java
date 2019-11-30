package com.mallcai.fulfillment.pe.common.utils;

import com.mallcai.fulfillment.pe.deploy.test.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-30 19:55:19
 */
public class RedisDistributeLockUtilTest extends BaseTestCase {

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    @Test
    public void testLock(){

        System.out.println(redisDistributeLockUtil.tryGetDistributedLock("123", "1224", 420));
    }

    @Test
    public void testUnlock(){

        System.out.println(redisDistributeLockUtil.releaseDistributedLock("123", "1224"));
    }
}
