package com.mallcai.manager.common.exception;

/**
 * IErrors
 * @author admin
 * @date 2019-08-13 23:47:40
 */
public interface IErrors {

  /**
   * 获取code.
   * @return
   */
  int getCode();

  /**
   * 获取信息.
   * @return
   */
  String getMessage();

  /**
   * getSolution
   * @return
   */
  String getSolution();
  
}
