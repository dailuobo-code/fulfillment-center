package com.mallcai.fulfillment.dp.api.request;

import com.mallcai.fulfillment.dp.api.enums.DeliveryTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 配送信息
 *
 * 今后有新的配送方式, 都会在该对象中进行收拢
 *
 * @author hupp create date: 17/11/28
 */
@Data
public class DeliveryInfoDTO implements Serializable {

  private static final long serialVersionUID = 3000368598523046860L;

  /**
   * 发货方式
   * @see DeliveryTypeEnum
   */
  private Integer deliveryType;

  /**
   * 快递的物流信息
   */
  private ExpressMerchantDTO expressMerchantDTO;

  /**
   * 收货人地址
   */
  private DeliveryAddressDTO receiverAddress;
  /**
   * 收货人信息
   */
  private DeliveryUserDTO receiverUser;

}
