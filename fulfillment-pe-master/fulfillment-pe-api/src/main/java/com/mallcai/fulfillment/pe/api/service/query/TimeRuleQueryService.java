package com.mallcai.fulfillment.pe.api.service.query;

import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.query.dto.req.SearchTimeRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.TimeRuleDTO;

import java.util.List;

/**
 * 时间规则查询
 */
public interface TimeRuleQueryService {

    /**
     * 查询详情
     * @param id 规则id
     * @return
     */
    PlainResult<TimeRuleDTO> getDetail(Long id);

    /**
     * 搜索时间规则
     * @param param 参数对象
     * @return
     */
    PlainResult<Paging<TimeRuleDTO>> searchRules(SearchTimeRuleRequestDTO param);

    /**
     * 查询所有配置了规则的门店id列表
     * @return 门店分组id列表
     */
    PlainResult<List<Integer>> getAllStoreIds();

    /**
     * 根据门店id查询时间规则信息
     * @param storeId
     * @return
     */
    PlainResult<TimeRuleDTO> getByStoreId(Integer storeId);
}
