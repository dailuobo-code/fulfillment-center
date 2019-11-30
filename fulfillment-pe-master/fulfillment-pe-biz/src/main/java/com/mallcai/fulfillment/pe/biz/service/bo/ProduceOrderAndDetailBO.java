package com.mallcai.fulfillment.pe.biz.service.bo;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 生产单及生产单与商品订单映射对象
 * @author: chentao
 * @create: 2019-08-24 17:31:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduceOrderAndDetailBO {

    private ProduceOrder produceOrder;

    private List<ProduceOrderDetail> produceOrderDetails;
}
