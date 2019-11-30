package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.PickingRuleOperateService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.AddPickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.DeletePickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OperatePickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.biz.service.inner.PickingRuleInnerService;
import com.mallcai.fulfillment.pe.biz.service.impl.transfor.PickingRuleConverter;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.domain.entity.PickingRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pickingRuleOperateService")
public class PickingRuleOperateServiceImpl implements PickingRuleOperateService {

    private PickingRuleConverter pickingRuleConverter = PickingRuleConverter.INSTANCE;

    @Autowired
    private PickingRuleInnerService pickingRuleInnerService;

    @Override
    public PlainResult<?> addRule(AddPickingRuleRequestDTO param) {
        AssertUtils.assertNotNull(param);
        AssertUtils.assertNotEmpty(param.getProductNos(), "商品信息不能为空");
        AssertUtils.assertNotNull(param.getUserId(), "操作人不能为空");
        AssertUtils.assertNotNull(param.getWarehouseId(), "仓库信息不能为空");

        List<PickingRule> pickingRules = pickingRuleConverter.toPickingRules(param);

        boolean success = pickingRuleInnerService.addRules(pickingRules);

        return success ? PlainResult.ok() : PlainResult.fail("保存失败");
    }

    @Override
    public PlainResult<?> updateRule(OperatePickingRuleRequestDTO param) {
        AssertUtils.assertNotNull(param);
        AssertUtils.assertNotNull(param.getId(), "id不能为空");
        AssertUtils.assertNotNull(param.getProductNo(), "商品信息不能为空");
        AssertUtils.assertNotNull(param.getUserId(), "操作人不能为空");
        AssertUtils.assertNotNull(param.getWarehouseId(), "仓库信息不能为空");

        PickingRule pickingRule = pickingRuleConverter.toPickingRule(param);
        pickingRule.setUpdateUserId(param.getUserId());

        boolean success = pickingRuleInnerService.updateRule(pickingRule);

        return success ? PlainResult.ok() : PlainResult.fail("保存失败");
    }

    @Override
    public PlainResult<?> deleteRule(DeletePickingRuleRequestDTO param) {
        AssertUtils.assertNotNull(param);
        AssertUtils.assertNotNull(param.getId(), "id不能为空");
        AssertUtils.assertNotNull(param.getUserId(), "操作人不能为空");

        boolean success = pickingRuleInnerService.deleteRule(param.getId(), param.getUserId());

        return success ? PlainResult.ok() : PlainResult.fail("删除失败");
    }
}
