package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddPickingRuleRequestDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 仓库id*/
    private Integer warehouseId;

    /** 商品编码 */
    private List<String> productNos;

    /** 操作人用户id*/
    private Integer userId;
}
