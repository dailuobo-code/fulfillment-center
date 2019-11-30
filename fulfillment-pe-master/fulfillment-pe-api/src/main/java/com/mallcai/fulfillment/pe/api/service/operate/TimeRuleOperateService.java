package com.mallcai.fulfillment.pe.api.service.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OperateTimeRuleRequestDTO;

/**
 * 时间规则 操作类
 */
public interface TimeRuleOperateService {

    /**
     * 新增时间规则, 通过 PlainResult.isSuccess() 判断是否新增成功
     * @return
     */
    PlainResult<?> addRule(OperateTimeRuleRequestDTO param);

    /**
     * 更新时间规则, 通过 PlainResult.isSuccess() 判断是否新增成功
     * @return
     */
    PlainResult<?> updateRule(OperateTimeRuleRequestDTO param);
}
