package com.mallcai.fulfillment.dp.api.response;

import com.mallcai.fulfillment.dp.api.common.dto.TradeOrderDTO;
import com.mallcai.fulfillment.dp.api.common.dto.TradeOrderDetailDTO;
import com.mallcai.fulfillment.dp.api.common.dto.TradePackageDetailDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description orderDto
 * @date 2019-07-16
 */
@Data
public class TradeOrderAssemblyDTO implements Serializable {

    private static final long serialVersionUID = -7715911604561819452L;

    /**
     * 订单数据
     */
    private TradeOrderDTO orderDO;

    /**
     * 订单条目
     */
    private List<TradeOrderDetailDTO> orderDetailDoList;

    /**
     * 订单IdList
     */
    private List<String> orderIdList;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 包裹list
     */
    private List<TradePackageDetailDTO> packageDetailDTO;
}
