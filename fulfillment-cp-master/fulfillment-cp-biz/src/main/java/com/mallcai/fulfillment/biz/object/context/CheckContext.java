package com.mallcai.fulfillment.biz.object.context;

import com.google.common.collect.Table;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Data
public class CheckContext {

  /**
   * 业务类型
   */
  private String bizTag;

  private Map<String,Object> bizParam;

  /**   配置信息   */
  private TaskConfig config;

  /**
   * 左右两边可以统一标准对账的索引
   */
  private List<String> index;
  /**
   * 索引数量
   */
  private int totalCount;


  /** 订单信息  */
  private String checkNo;
  private Date checkTime;
  /**
   * 主要用于存放key,源头,key的总数,比如：cityProductId,履约方,该品的总数或者cityProductId,交易方,该品的总数
   */
  private Table<String,String,Object> result;
  /**
   * 主要用于存放结果对应的源头索引，比如：cityProductId,履约方,订单号或者cityProductId,交易方,订单号
   */
  private Table<Object,String,List<String>> keyToSourceTable;

  /**
   *  错误键值列表
   */
  private List<Object> errorKeyList;

  /**
   * 所检查的表内开始时间和结束时间
   */
  private Date beginTime;
  private Date endTime;
  /**
   * 统计成功时是否需要更新redis,默认为true
   */
  private boolean ifNeedUpdateRedis=true;



}
