package com.mallcai.fulfillment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * dp错误code 范围  180100000 ～ 180199999
 * @author admin
 * @date 2019年08月13日23:43:10
 */
@Data
@AllArgsConstructor
public class Errors implements IErrors {

    private int code;
    private String message;
    private String solution;

    /**
     * 公共异常  由于代码是从交易迁移过来的，为了上游不受影响：120100000  120100001  这两个错误码保持不变，后面均使用180100000 ～ 180199999
     */
    public static Errors FULFILLMENT_SYSTEM_ERROR = new Errors(120100000, "系统异常", "");
    public static Errors FULFILLMENT_PARAMS_ERROR = new Errors(120100001, "参数异常", "");
    public static Errors RPC_CALL_FAILED = new Errors(180100002, "调用远程服务失败", "");
    public static Errors DB_EXECUTE_EXCEPTION = new Errors(180100003, "数据库执行失败", "");
    public static Errors ORDERID_NOT_EXIST_EXCEPTION = new Errors(180100004, "订单不存在", "");

    /**
     * 30-39 订单类错误
     */
    public static Errors NUMBER_TYPE_NOT_MATCHING = new Errors(120303000, "编号类型不匹配", "");


}
