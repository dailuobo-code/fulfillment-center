package com.mallcai.fulfillment.biz.service;

import com.mallcai.fulfillment.biz.dto.TmsOrderVO;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18 16:09:05
 */
public interface TmsCarArrangmentClientService {

    TmsOrderVO sendCarArrangmentCommand(Integer storeId);
}
