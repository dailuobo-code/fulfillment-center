package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OperateTimeRuleRequestDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 主键*/
    private Long id;

    /** 城市id*/
    private Integer cityId;

    /** 仓库id*/
    private Integer warehouseId;

    /** 门店列表 */
    private List<Integer> storeIds;

    /** 取货时间 T+N 机制, N就是这里的deliveryDays*/
    private Integer deliveryDays;

    /** 操作人用户id*/
    private Integer userId;
}
