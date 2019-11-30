package com.mallcai.fulfillment.gateway.api.service;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.MultiPkgModifyExpressDTO;

import java.util.Set;

/**
 * @author gaoguoming
 * @date 2019-11-12 21:42:51
 */
public interface DeliveryOrderOperateDelegationService {

    PlainResult<Boolean> modifyMultiPkgExpress(Set<Integer> cityIds, MultiPkgModifyExpressDTO multiPkgModifyExpressDTO);
}
