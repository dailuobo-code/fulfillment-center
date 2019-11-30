package com.mallcai.fulfillment.common.rpc;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;

/**
 * @author admin
 * @date 2019年08月13日23:43:10
 */
@Getter
@Setter
public class RpcOption {

  /**
   * rpc请求参数
   */
  private Object requestDTO;

  /**
   * 调用方类名
   */
  private String callerClass;

  /**
   * 调用方方法名
   */
  private String callerFunc;

  /**
   * 具体的调用执行
   */
  private Callable<?> callSegment;

  /**
   * rpc调用的重试策略
   */
  private RpcRetryStrategy rpcRetryStrategy;

  /**
   * 是否记录调用日志, 默认是记录的
   *
   */
  private boolean addLog = true;

}
