package com.mallcai.fulfillment.pe.domain;

import com.mallcai.fulfillment.pe.domain.entity.TimeRule;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TimeRuleDO;

import java.util.ArrayList;
import java.util.List;

public class TimeRuleConverter {

    public static TimeRuleConverter INSTANCE = new TimeRuleConverter();

    public TimeRule toTimeRule(TimeRuleDO timeRule) {
        if(null == timeRule){
            return null;
        }

        TimeRule target = new TimeRule();

        target.setId(timeRule.getId());
        target.setCityId(timeRule.getCityId());
        target.setDeliveryDays(timeRule.getDeliveryDays());
        target.setWarehouseId(timeRule.getWarehouseId());

        target.setCreateTime(timeRule.getCreateTime());
        target.setCreateUserId(timeRule.getCreateUserId());

        target.setUpdateTime(timeRule.getUpdateTime());
        target.setUpdateUserId(timeRule.getUpdateUserId());

        return target;
    }

    public TimeRuleDO toTimeRuleDO(TimeRule timeRule){
        if(null == timeRule){
            return null;
        }

        TimeRuleDO target = new TimeRuleDO();

        target.setCityId(timeRule.getCityId());
        target.setId(timeRule.getId());
        target.setDeliveryDays(timeRule.getDeliveryDays());
        target.setWarehouseId(timeRule.getWarehouseId());

        target.setCreateTime(timeRule.getCreateTime());
        target.setCreateUserId(timeRule.getCreateUserId());

        target.setUpdateTime(timeRule.getUpdateTime());
        target.setUpdateUserId(timeRule.getUpdateUserId());

        return target;
    }

    public List<TimeRule> toTimeRuleList(List<TimeRuleDO> timeRules) {
        if(null == timeRules){
            return null;
        }

        List<TimeRule> target = new ArrayList<>(timeRules.size());
        timeRules.forEach(ele -> target.add(this.toTimeRule(ele)));

        return target;
    }
}
