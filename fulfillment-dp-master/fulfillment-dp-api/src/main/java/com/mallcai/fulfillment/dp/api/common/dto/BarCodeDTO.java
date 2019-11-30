package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yl
 * @description 条码
 * @date 2019-07-15
 */
@Data
public class BarCodeDTO implements Serializable {

    private static final long serialVersionUID = 7256186205961179362L;
    /**
     * 码号
     */
    private String barCode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 分拣信息
     */
    private String sorting;

}
