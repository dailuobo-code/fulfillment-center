package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperatePickingRuleRequestDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 主键 */
    private Long id;

    /** 仓库id*/
    private Integer warehouseId;

    /** 商品编码 */
    private String productNo;

    /** 操作人用户id*/
    private Integer userId;
}
