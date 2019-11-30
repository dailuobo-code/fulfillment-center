package com.mallcai.fulfillment.dc.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * dc错误code 范围  120100000 ～ 120199999
 * pe错误code 范围  120200000 ～ 120299999
 * dp错误code 范围  120300000 ～ 120399999
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
@Data
@AllArgsConstructor
public class Errors implements IErrors {

    private int code;
    private String message;
    private String solution;

    /**
     * 公共异常
     */
    public static Errors FULFILLMENT_SYSTEM_ERROR = new Errors(120100000, "系统异常", "");
    public static Errors FULFILLMENT_PARAMS_ERROR = new Errors(120100001, "参数异常", "");
    public static Errors RPC_CALL_FAILED = new Errors(180100002, "调用远程服务失败", "");
    public static Errors DB_EXECUTE_EXCEPTION = new Errors(180100003, "数据库执行失败", "");
    /**
     * 30-39 订单类错误
     */
    public static Errors NUMBER_TYPE_NOT_MATCHING = new Errors(120303000, "编号类型不匹配", "");

    /**
     * 不存在的事件
     */
    public static Errors EVENT_NOT_EXISTS = new Errors(120100100, "不存在的事件", "");


    /**
     * 180101000-180101100 流程配置相关
     */
    public static Errors FLOW_CONFIG_ERROR = new Errors(180101000, "流程配置错误", "");
    public static Errors FLOW_NOT_EXIST_ERROR = new Errors(180101001, "流程不存在", "");
}
