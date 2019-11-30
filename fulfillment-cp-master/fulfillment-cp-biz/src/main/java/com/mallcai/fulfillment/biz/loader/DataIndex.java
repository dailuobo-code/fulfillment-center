package com.mallcai.fulfillment.biz.loader;


import com.mallcai.fulfillment.biz.object.context.CheckContext;

import java.util.List;


public interface DataIndex {

  /**
   * 根据 参数获取对账索引
   * @param context
   * @return
   */
  List<String> findIndex(CheckContext context,int start,int pageSize);

  /**
   * 获取本次任务要跑的数据总量
   * @param context
   * @return
   */
  int getTotalCount(CheckContext context);

}
