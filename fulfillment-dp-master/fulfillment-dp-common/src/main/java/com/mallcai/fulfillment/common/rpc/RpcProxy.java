package com.mallcai.fulfillment.common.rpc;


import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.utils.JsonUtils;
import com.mallcai.fulfillment.common.exception.BizException;
import com.mallcai.fulfillment.common.exception.Errors;
import lombok.extern.slf4j.Slf4j;


/**
 * rpc调用代理
 * @author admin
 * @date 2019年08月13日23:43:10
 */
@Slf4j
public class RpcProxy {

  /**
   * 调用端可直接关心结果
   * @param rpcOpt
   * @return PlainResult | null
   */
  public static <T> PlainResult call(RpcOption rpcOpt) {
    String caller  = rpcOpt.getCallerClass() + "." + rpcOpt.getCallerFunc();
    String request = JsonUtils.toJson(rpcOpt.getRequestDTO());

    try {
      if (rpcOpt.isAddLog()) {
        log.info("[{}] requestDTO={}", caller, request);
      }
      long start = System.currentTimeMillis();

      PlainResult result = (PlainResult) rpcOpt.getCallSegment().call();

      if (rpcOpt.isAddLog()) {
        log.info("[{}] result={} requestDTO={} useTime={}",
                      caller, JsonUtils.toJson(result), request, System.currentTimeMillis() - start);
      }

      if (result == null) {
        log.info("[{}] result is null, request={}", caller, request);
        throw new BizException(Errors.RPC_CALL_FAILED);
      }

      if (!result.isSuccess()) {
        return result;
      }

      if (result.getData() == null) {
        log.info("[{}] result.data=null, request={}", caller, request);
      }

      return result;
    } catch (Exception e) {
      log.error("[{}] failure. request={} e={}", caller, request, e);
      return null;
    }
  }

}
