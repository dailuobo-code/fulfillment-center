package com.mallcai.fulfillment.biz.service;

import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Liu Yang
 * @description redis操作服务
 * @date: 2019-10-11 17:21
 */
@Service
@Slf4j
public class RedisService {
  @Autowired
  private JedisClientUtil jedisClientUtil;

  /**
   * 设置redis值
   * @param key
   * @param value
   * @param time 过期时间 单位
   * @return
   */
  public boolean set(String key,String value,int time){
    try {
      jedisClientUtil.set(key,value);
    }catch (Exception e){
      log.error("设置redis值失败,key:{},value:{},time:{}",key,value,time,e);
      return false;
    }
    try {
      jedisClientUtil.expire(key,time);
    }catch (Exception e){
      log.error("设置redis过期时间失败,key:{},value:{},time:{}",key,value,time,e);
      try {
        jedisClientUtil.del(key);
      }catch (Exception ex){
        log.error("删除redis的key失败,key:{},value:{},time:{}",key,value,time,ex);
      }
      return false;
    }
    return true;
  }

  /**
   * 设置redis值
   * @param key
   * @param value
   * @param time 过期时间 单位
   * @return
   */
  public boolean incrBy(String key,Integer value,int time){
    try {
      jedisClientUtil.incrBy(key,value);
    }catch (Exception e){
      log.error("设置redis值失败,key:{},value:{},time:{}",key,value,time,e);
      return false;
    }
    try {
      jedisClientUtil.expire(key,time);
    }catch (Exception e){
      log.error("设置redis过期时间失败,key:{},value:{},time:{}",key,value,time,e);
      try {
        jedisClientUtil.del(key);
      }catch (Exception ex){
        log.error("删除redis的key失败,key:{},value:{},time:{}",key,value,time,ex);
      }
      return false;
    }
    return true;
  }

  /**
   * redis值,计数加
   * @param key
   * @param value
   * @return
   */
  public boolean incrBy(String key,Integer value){
    try {
      jedisClientUtil.incrBy(key,value);
    }catch (Exception e){
      log.error("设置redis值失败,key:{},value:{},time:{}",key,value,e);
      return false;
    }
    return true;
  }

  /**
   * redis值，计数减
   * @param key
   * @param value
   * @return
   */
  public boolean decrBy(String key,Integer value){
    try {
      jedisClientUtil.decrBy(key,value);
    }catch (Exception e){
      log.error("设置redis值失败,key:{},value:{},time:{}",key,value,e);
      return false;
    }
    return true;
  }

  /**
   * 获取redis值
   * @param key
   * @return
   */
  public String get(String key){
    String result=null;
    try {
      result=jedisClientUtil.get(key);
    }catch (Exception e){
      log.error("获取redis值失败,key:{},",key,e);
    }
    return result;
  }


}
