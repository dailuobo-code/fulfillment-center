package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description MGR导入表格的数据
 * @date: 2019-10-10 10:53
 */
@Data
public class DeliveryGoodsDTO implements Serializable {

  private static final long serialVersionUID = 5397679959059282546L;
  /**
   * 导入表格的明细列表
   */
  private List<DeliveryGoodsItemRequestDTO> deliveryGoodsItemRequestDTOList;
}
