package com.mallcai.fulfillment.pe.api.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-10-13 21:17:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduceOrderGoodsInfoListMsg {

    private List<ProduceOrderGoodsInfoMsg> produceOrderGoodsInfoMsgs;
}
