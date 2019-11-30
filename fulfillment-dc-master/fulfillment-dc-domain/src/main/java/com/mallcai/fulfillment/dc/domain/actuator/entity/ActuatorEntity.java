package com.mallcai.fulfillment.dc.domain.actuator.entity;

/**
 * 执行器领域对象
 * @author bh.zhong
 * @date 2019/8/15 2:36 PM
 */
public class ActuatorEntity {

    /**
     * 流程名称
     */
    private String actuatorStepName;
    /**
     * 流程类型
     */
    private String actuatorStepType;
    /**
     * 执行器步骤标识: 如执行订单状态修改，在执行库存扣减  执行前：
     */
    private String  actoatorId;

}
