package com.mallcai.fulfillment.pe.biz.service.impl.query;

import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.query.TimeRuleQueryService;
import com.mallcai.fulfillment.pe.api.service.query.dto.req.SearchTimeRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.TimeRuleDTO;
import com.mallcai.fulfillment.pe.biz.service.inner.TimeRuleInnerService;
import com.mallcai.fulfillment.pe.biz.service.impl.transfor.TimeRuleConverter;
import com.mallcai.fulfillment.pe.common.page.PagedSearch;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.domain.entity.TimeRule;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchTimeRuleCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("timeRuleQueryService")
public class TimeRuleQueryServiceImpl implements TimeRuleQueryService {

    @Autowired
    private TimeRuleInnerService timeRuleInnerService;

    private TimeRuleConverter timeRuleConverter = TimeRuleConverter.INSTANCE;

    @Override
    public PlainResult<TimeRuleDTO> getDetail(Long id) {
        AssertUtils.assertNotNull(id, "id不能为空");

        TimeRule timeRule = timeRuleInnerService.loadById(id);

        PlainResult<TimeRuleDTO> result = new PlainResult<>();
        result.setResult(timeRuleConverter.toTimeRuleDTO(timeRule));

        return result;
    }

    @Override
    public PlainResult<Paging<TimeRuleDTO>> searchRules(SearchTimeRuleRequestDTO param) {
        AssertUtils.assertNotNull(param);

        // 参数转换
        SearchTimeRuleCondition condition = timeRuleConverter.toSearchTimeRuleCondition(param);

        // 分页查询
        PagedSearch<TimeRule> pagedSearch = timeRuleInnerService.searchRules(condition);

        // 结果转换
        PlainResult<Paging<TimeRuleDTO>> result = new PlainResult<>();
        Paging<TimeRuleDTO> data = new Paging<>();
        data.setTotal(pagedSearch.getTotal());
        data.setData(timeRuleConverter.toTimeRuleDTOList(pagedSearch.getData()));
        result.setResult(data);

        return result;
    }

    @Override
    public PlainResult<List<Integer>> getAllStoreIds() {
        List<Integer> ids = timeRuleInnerService.getAllStoreIds();

        PlainResult<List<Integer>> result = new PlainResult<>();
        result.setResult(ids);

        return result;
    }

    @Override
    public PlainResult<TimeRuleDTO> getByStoreId(Integer storeId) {
        AssertUtils.assertNotNull(storeId, "门店id不能为空");

        TimeRule timeRule = timeRuleInnerService.loadByStoreId(storeId);

        PlainResult<TimeRuleDTO> result = new PlainResult<>();
        result.setResult(timeRuleConverter.toTimeRuleDTO(timeRule));

        return result;
    }
}
