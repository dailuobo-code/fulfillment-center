package com.mallcai.fulfillment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * pe错误code 范围  180200000 ～ 180299999
 * @author admin
 * @date 2019年08月13日23:43:10
 */
@Data
@AllArgsConstructor
public class Errors implements IErrors {

  private int code;
  private String message;
  private String solution;

  /**
   * 公共异常
   */
  public static Errors FULFILLMENT_SYSTEM_ERROR = new Errors(180200000, "系统异常", "");
  public static Errors FULFILLMENT_PARAMS_ERROR = new Errors(180200001, "参数异常", "");

  /**
   * 00200-00299 内部数据异常
   */
  public static Errors DATA_ERROR = new Errors(180200200, "内部数据异常", "");
  public static Errors MQ_SENDMSG_ERROR = new Errors(180200201, "发送消息异常", "");

  /**
   * 00300-00399 依赖服务异常
   */
  public static Errors DEPENDENCY_SERVICE_EXCEPTION = new Errors(180200300, "依赖服务异常", "");
  public static Errors DEPENDENCY_DATA_ERROR = new Errors(180200301, "依赖服务数据异常", "");


  /**
   * 180200400-180200499 配置相关错误
   */
  public static Errors RULE_ALREADY_EXISTS = new Errors(180200400, "时间配置已经存在，请不要重复提交", "");
  public static Errors UPDATE_RULE_ERROR = new Errors(180200401, "更新配置失败", "");
  public static Errors RULE_NOT_EXISTS = new Errors(180200402, "规则配置不存在", "");
  public static Errors CONFIG_CONTENT_ERROR = new Errors(180200403, "配置内容有误", "");

  /**
   * 00500-00699 订单相关
   */
  public static Errors ORDER_EXISTED = new Errors(180200500, "订单已存在", "");
  public static Errors ORDER_CAN_NOT_CANCEL = new Errors(180200501, "订单不可取消", "");
  public static Errors ORDER_CANCEL_EXCEPTION = new Errors(180200502, "订单取消异常", "");
  public static Errors ORDER_CANCELED = new Errors(180200503, "订单已取消", "");
  public static Errors ORDER_GROUP_VALUE_ERROR = new Errors(180200504, "订单groupValue值有误", "");



  /**
   * 00700-00799 积单相关
   */
  public static Errors BATCH_AGGREGATE_ORDER_ERROR = new Errors(180200700, "批次集单失败", "");
  public static Errors BATCH_AGGREGATE_ORDER_MESSAGE_SEND_ERROR = new Errors(180200701, "批次集单消息分割异常", "");
  public static Errors AGGREGATE_ORDER_MESSAGE_HANDLE_ERROR = new Errors(180200702, "开始集单消息处理异常", "");



  /**
   * 00800-00899 推单相关
   */
  public static Errors ERP_MESSAGE_ERROR = new Errors(180200801, "发送erp消息异常", "");

  public static Errors PRODUCE_ORDER_GOODS_INFO_ERROR = new Errors(180200802, "发送生产单消息没有货品信息异常", "");


  /**
   * 00900-00999 业务场景锁相关
   */
  public static Errors AGGREGATE_ORDER_DATA_RELOAD_LOCK_ERROR = new Errors(180200900, "获取数据重入与集单竞争锁失败", "");

  /**
   * 08000-08100 分布式处理
   */
  public static Errors BATCH_DATA_TASK_SEND_ERROR = new Errors(180208000, "批处理任务发送异常", "");
  public static Errors BATCH_DATA_SPLIT_ERROR = new Errors(180208001, "分布式处理分割任务异常", "");


}
