package com.mallcai.fulfillment.dc.common.exception;

/**
 * IErrors
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public interface IErrors {

    /**
     * 获取code.
     *
     * @return
     */
    int getCode();

    /**
     * 获取信息.
     *
     * @return
     */
    String getMessage();

    /**
     * getSolution
     *
     * @return
     */
    String getSolution();

}
