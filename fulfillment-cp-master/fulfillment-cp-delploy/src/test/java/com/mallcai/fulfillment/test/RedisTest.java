package com.mallcai.fulfillment.test;


import com.mallcai.fulfillment.deploy.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Autowired
    private JedisClientUtil jedisClientUtil;


    @Test
    public void testJedis(){
        //设置 key 和 失效时间(单位s)
        jedisClientUtil.setex("testKey1",100,"testValue");
        // 分布式锁使用方式,只能 key 不存在的时候,在设置 key 的值
        jedisClientUtil.set("testKey2","testValue", JedisClientUtil.SetPremise.NX,JedisClientUtil.ExpireType.Seconds,10);


        String testvalue = jedisClientUtil.get("testKey1");

        System.out.println(testvalue);

    }


}
