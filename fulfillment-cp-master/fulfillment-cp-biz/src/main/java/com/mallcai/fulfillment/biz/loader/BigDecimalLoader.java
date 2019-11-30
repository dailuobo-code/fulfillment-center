package com.mallcai.fulfillment.biz.loader;


import com.mallcai.fulfillment.biz.object.context.CheckContext;

import java.math.BigDecimal;
import java.util.Map;


/**
 * @description 大数loader
 * @author liuyang
 */
public interface BigDecimalLoader {

  /**
   * 根据 index 查询对账的参数
   */
  Map<String, BigDecimal> loadResource(CheckContext checkContext);


}
