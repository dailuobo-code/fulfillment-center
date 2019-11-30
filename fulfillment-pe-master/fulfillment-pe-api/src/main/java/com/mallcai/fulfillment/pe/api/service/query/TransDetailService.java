package com.mallcai.fulfillment.pe.api.service.query;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.WareTransInfo;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @description: 排车计划查询接口
 * @author: zhj
 * @create: 2019-08-27 22:56:47
 */
public interface TransDetailService {

    // 标品排车计划
    PlainResult<List<WareTransInfo>> standTransPlan(List<Integer> storeIds, Date date);

    // 冻品排车计划
    PlainResult<List<WareTransInfo>> fronzeTransPlan(List<Integer> storeIds, Date date);

    // 生鲜排车计划
    PlainResult<List<WareTransInfo>> freshTransPlan(List<Integer> storeIds, Date date);
}
