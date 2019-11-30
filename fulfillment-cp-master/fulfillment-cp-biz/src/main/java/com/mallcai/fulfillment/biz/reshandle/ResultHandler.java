package com.mallcai.fulfillment.biz.reshandle;


import com.mallcai.fulfillment.biz.object.context.CheckContext;

public interface ResultHandler {

  /**
   * 处理结果
   * @param checkContext
   */
  void handleResult(CheckContext checkContext);

}
