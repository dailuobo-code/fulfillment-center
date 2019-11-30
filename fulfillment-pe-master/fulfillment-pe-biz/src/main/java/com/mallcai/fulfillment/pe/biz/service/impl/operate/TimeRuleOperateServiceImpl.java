package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.TimeRuleOperateService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OperateTimeRuleRequestDTO;
import com.mallcai.fulfillment.pe.biz.service.inner.TimeRuleInnerService;
import com.mallcai.fulfillment.pe.biz.service.impl.transfor.TimeRuleConverter;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.domain.entity.TimeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("timeRuleOperateService")
public class TimeRuleOperateServiceImpl implements TimeRuleOperateService {

    private TimeRuleConverter timeRuleConverter = TimeRuleConverter.INSTANCE;

    @Autowired
    private TimeRuleInnerService timeRuleInnerService;

    @Override
    public PlainResult<?> addRule(OperateTimeRuleRequestDTO param) {
        // 参数校验
        checkOperateInput(param);

        // 参数转换
        TimeRule timeRule = timeRuleConverter.toTimeRule(param);
        timeRule.setCreateUserId(param.getUserId());
        timeRule.setUpdateUserId(param.getUserId());

        return timeRuleInnerService.addRule(timeRule) ? PlainResult.ok() : PlainResult.fail("新增失败");
    }

    @Override
    public PlainResult<?> updateRule(OperateTimeRuleRequestDTO param) {
        checkOperateInput(param);
        AssertUtils.assertNotNull(param.getId(), "id不能为空");

        // 参数转换
        TimeRule timeRule = timeRuleConverter.toTimeRule(param);
        timeRule.setUpdateUserId(param.getUserId());

        return timeRuleInnerService.updateRule(timeRule) ? PlainResult.ok() : PlainResult.fail("更新失败");
    }

    private void checkOperateInput(OperateTimeRuleRequestDTO param){
        AssertUtils.assertNotNull(param);
        AssertUtils.assertNotNull(param.getCityId(), "城市id不能为空");
        AssertUtils.assertNotNull(param.getDeliveryDays(), "取货时间不能为空");
        AssertUtils.assertNotNull(param.getWarehouseId(), "仓库id不能为空");
        AssertUtils.assertNotEmpty(param.getStoreIds(), "门店信息不能为空");
        AssertUtils.assertNotNull(param.getUserId(), "操作人信息不能为空");
    }
}
