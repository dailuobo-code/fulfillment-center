package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import java.io.Serializable;
import lombok.Data;

/**
 * MGR导入表格明细
 * @author Liu Yang
 * @date 2019/10/10 10:00 AM
 */
@Data
public class DeliveryGoodsItemRequestDTO implements Serializable {

    private static final long serialVersionUID = 5367220514090380625L;

    /**
     * 订单类型 目前只有生鲜：1
     */
    private Integer orderType;
    /**
     * 总部商品ID
     */
    private String productNo;
    /**
     * 城市商品ID
     */
    private Integer cityProductId;
    /**
     * 产品数量
     */
    private Integer productNum;
    /**
     * 门店ID
     */
    private Integer storeId;
    /**
     * 仓库ID
     */
    private Integer warehouseId;
    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 导入时间，后面订单的取货时间和推单时间都以这个为准
     */
    private String importTime;

}
