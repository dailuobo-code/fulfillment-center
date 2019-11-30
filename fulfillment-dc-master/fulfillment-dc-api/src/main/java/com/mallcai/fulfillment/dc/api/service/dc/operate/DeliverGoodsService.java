package com.mallcai.fulfillment.dc.api.service.dc.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DeliveryGoodsDTO;

/**
 * @author: Liu Yang
 * @description MGR导入表格
 * @date: 2019-10-10 10:41
 */
public interface DeliverGoodsService {

  /**
   * MGR导入数据
   * @param deliveryGoodsDTO
   * @return
   */
  PlainResult<Boolean> importGoodsCsv(DeliveryGoodsDTO deliveryGoodsDTO);

  /**
   * 重新调用PE的导入MGR接口
   * @param importTime
   * @return
   */
  PlainResult<Boolean> reInvokePeImportMgrService(String importTime);
}
