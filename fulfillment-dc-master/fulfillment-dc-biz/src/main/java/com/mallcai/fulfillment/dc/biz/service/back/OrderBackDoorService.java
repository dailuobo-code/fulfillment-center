package com.mallcai.fulfillment.dc.biz.service.back;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.trade.common.model.SOAOrderDetailVO;

/**
 * @author bh.zhong
 * @date 2019/11/7 3:38 PM
 */
public interface OrderBackDoorService {

    PlainResult<?> backReceiveOrder(SOAOrderDetailVO soaOrderDetailVO);

}
