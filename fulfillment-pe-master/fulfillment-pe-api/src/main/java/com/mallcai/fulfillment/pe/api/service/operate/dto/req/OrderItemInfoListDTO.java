package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-10 21:41
 */
@Data
public class OrderItemInfoListDTO implements Serializable {

  private static final long serialVersionUID = 301891432432418749L;
  List<OrderItemInfoDTO> orderItemInfoDOList;
}
