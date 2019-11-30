package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description 商品信息
 * @date 2019-07-15
 */
@Data
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = -4815773406672479914L;
    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品id
     */
    private Integer cityProductId;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 条形码信息
     */
    private List<BarCodeDTO> barCodeDtoList;

    /**
     * 商品名称
     **/
    private String productName;
}
