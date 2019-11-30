package com.mallcai.fulfillment.biz.loader;


import com.mallcai.fulfillment.biz.object.context.CheckContext;

import java.util.Map;


public interface Loader {

  /**
   * 根据 index 查询对账的参数
   */
  Map<String,Integer> loadResource(CheckContext checkContext);


}
