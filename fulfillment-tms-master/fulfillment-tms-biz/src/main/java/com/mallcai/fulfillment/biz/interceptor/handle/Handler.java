package com.mallcai.fulfillment.biz.interceptor.handle;

/**
 * handler
 * @author admin
 * @date 2019-08-13 23:46:00
 */
public interface Handler {

  /**
   * 处理器
   * @param handleRequest
   * @return
   */
  HandleResponse handle(HandleRequest handleRequest);


}
