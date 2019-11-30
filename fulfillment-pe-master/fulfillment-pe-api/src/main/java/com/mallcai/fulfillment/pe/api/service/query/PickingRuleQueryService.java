package com.mallcai.fulfillment.pe.api.service.query;

import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.PagedDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.req.SearchPickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.PickingRuleDTO;

/**
 * 拣货规则查询接口
 */
public interface PickingRuleQueryService {

    /**
     * 查询详情
     * @param id 规则id
     * @return
     */
    PlainResult<PickingRuleDTO> getDetail(Long id);

    /**
     * 搜索时间规则
     * @param param 参数对象
     * @return
     */
    PlainResult<Paging<PickingRuleDTO>> searchRules(SearchPickingRuleRequestDTO param);
}
