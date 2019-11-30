package com.mallcai.fulfillment.common.rpc;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Rpc调用的重试策略
 * @author mengru
 * @date 18/1/18
 */
@Getter
@Setter
public class RpcRetryStrategy {
  /**
   * 失败是否重试
   */
  private Boolean failRetry;

  /**
   * 定义需要重试的错误码 如果为空 并且指定重试 那就忽视错误码的判断
   */
  private List<Integer> retryCodes;

  /**
   * 当前调用次数
   */
  private Integer currentCallCount = 1;

  public RpcRetryStrategy(Boolean failRetry, List<Integer> retryCodes) {
    this.failRetry = failRetry;
    this.retryCodes = retryCodes;
  }
}
