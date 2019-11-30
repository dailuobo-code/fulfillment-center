package com.mallcai.fulfillment.pe.biz.service.impl.transfor;

import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OperateTimeRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.req.SearchTimeRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.TimeRuleDTO;
import com.mallcai.fulfillment.pe.domain.entity.TimeRule;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchTimeRuleCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间规则相关转换器
 */
public class TimeRuleConverter {

    public static TimeRuleConverter INSTANCE = new TimeRuleConverter();

    public TimeRule toTimeRule(OperateTimeRuleRequestDTO param) {
        if (null == param) {
            return null;
        }
        TimeRule timeRule = new TimeRule();
        timeRule.setId(param.getId());
        timeRule.setCityId(param.getCityId());
        timeRule.setDeliveryDays(param.getDeliveryDays());
        timeRule.setStoreIds(param.getStoreIds());
        timeRule.setWarehouseId(param.getWarehouseId());
        return timeRule;
    }

    public TimeRuleDTO toTimeRuleDTO(TimeRule timeRule) {
        if (null == timeRule) {
            return null;
        }
        TimeRuleDTO timeRuleDTO = new TimeRuleDTO();
        timeRuleDTO.setCityId(timeRule.getCityId());
        timeRuleDTO.setId(timeRule.getId());
        timeRuleDTO.setDeliveryDays(timeRule.getDeliveryDays());
        timeRuleDTO.setStoreIds(timeRule.getStoreIds());
        timeRuleDTO.setWarehouseId(timeRule.getWarehouseId());
        timeRuleDTO.setCreateTime(timeRule.getCreateTime());
        timeRuleDTO.setCreateUserId(timeRule.getCreateUserId());
        timeRuleDTO.setUpdateTime(timeRule.getUpdateTime());
        timeRuleDTO.setUpdateUserId(timeRule.getUpdateUserId());
        return timeRuleDTO;
    }

    public SearchTimeRuleCondition toSearchTimeRuleCondition(SearchTimeRuleRequestDTO param) {
        if (null == param) {
            return null;
        }
        SearchTimeRuleCondition condition = new SearchTimeRuleCondition();
        condition.setPage(param.getPage(), 1);
        condition.setPageSize(param.getPageSize(), 20);
        return condition;
    }

    public List<TimeRuleDTO> toTimeRuleDTOList(List<TimeRule> data) {
        if (null == data) {
            return null;
        }
        List<TimeRuleDTO> target = new ArrayList<>(data.size());
        data.forEach(ele -> target.add(this.toTimeRuleDTO(ele)));
        return target;
    }
}
