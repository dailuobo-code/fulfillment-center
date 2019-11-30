package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.mallcai.fulfillment.dc.biz.service.enums.StepResultStatusEnum;
import lombok.Data;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17
 */
@Data
public class StepResult {

    private int stepResult;

    public boolean isSuc(){

        return StepResultStatusEnum.EXECUTE_SUCCESS.getCode() == stepResult;
    }

    public static StepResult succ() {
        StepResult StepResult = new StepResult();
        StepResult.setStepResult(StepResultStatusEnum.EXECUTE_SUCCESS.getCode());
        return StepResult;
    }

    public static StepResult fail() {
        StepResult StepResult = new StepResult();
        StepResult.setStepResult(StepResultStatusEnum.EXECUTE_FAIL.getCode());
        return StepResult;
    }
}
