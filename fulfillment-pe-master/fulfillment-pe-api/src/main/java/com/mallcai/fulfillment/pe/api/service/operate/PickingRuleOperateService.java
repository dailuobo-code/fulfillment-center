package com.mallcai.fulfillment.pe.api.service.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.AddPickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.DeletePickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OperatePickingRuleRequestDTO;

/**
 * 拣货规则操作接口
 */
public interface PickingRuleOperateService {

    /**
     *  新增规则, 通过 PlainResult.isSuccess() 判断是否新增成功
     */
    PlainResult<?> addRule(AddPickingRuleRequestDTO param);

    /**
     * 更新规则, 通过 PlainResult.isSuccess() 判断是否新增成功
     */
    PlainResult<?> updateRule(OperatePickingRuleRequestDTO param);

    /**
     * 删除规则, 通过 PlainResult.isSuccess() 判断是否新增成功
     */
    PlainResult<?> deleteRule(DeletePickingRuleRequestDTO param);
}
