package com.mallcai.fulfillment.pe.biz.service.impl.query;

import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.query.PickingRuleQueryService;
import com.mallcai.fulfillment.pe.api.service.query.dto.req.SearchPickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.PickingRuleDTO;
import com.mallcai.fulfillment.pe.biz.service.inner.PickingRuleInnerService;
import com.mallcai.fulfillment.pe.biz.service.impl.transfor.PickingRuleConverter;
import com.mallcai.fulfillment.pe.common.page.PagedSearch;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.domain.entity.PickingRule;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchPickingRuleCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pickingRuleQueryService")
public class PickingRuleQueryServiceImpl implements PickingRuleQueryService {

    private PickingRuleConverter pickingRuleConverter = PickingRuleConverter.INSTANCE;

    @Autowired
    private PickingRuleInnerService pickingRuleInnerService;

    @Override
    public PlainResult<PickingRuleDTO> getDetail(Long id) {
        AssertUtils.assertNotNull(id, "id不能为空");

        PickingRule pickingRule = pickingRuleInnerService.loadById(id);

        PlainResult<PickingRuleDTO> result = new PlainResult<>();
        result.setResult(pickingRuleConverter.toPickingRuleDTO(pickingRule));

        return result;
    }

    @Override
    public PlainResult<Paging<PickingRuleDTO>> searchRules(SearchPickingRuleRequestDTO param) {
        AssertUtils.assertNotNull(param);

        SearchPickingRuleCondition condition = pickingRuleConverter.toSearchPickingRuleCondition(param);

        PagedSearch<PickingRule> pagedSearch = pickingRuleInnerService.searchRules(condition);

        // 结果转换
        PlainResult<Paging<PickingRuleDTO>> result = new PlainResult<>();
        Paging<PickingRuleDTO> data = new Paging<>();
        data.setTotal(pagedSearch.getTotal());
        data.setData(pickingRuleConverter.toPickingRuleDTOList(pagedSearch.getData()));
        result.setResult(data);

        return result;
    }
}
