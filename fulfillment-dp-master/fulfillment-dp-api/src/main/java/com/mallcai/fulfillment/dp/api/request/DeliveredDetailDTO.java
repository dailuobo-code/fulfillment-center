package com.mallcai.fulfillment.dp.api.request;

import com.mallcai.fulfillment.dp.api.common.dto.DeliveredOrder;
import com.mallcai.fulfillment.dp.api.common.dto.SettlementLogDTO;
import lombok.Data;

import com.mallcai.fulfillment.dp.api.enums.PickUpSourceEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description 交付详情DTO
 * @date 2019-07-15
 */
@Data
public class DeliveredDetailDTO implements Serializable {

    private static final long serialVersionUID = 734996732701377836L;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 门店id
     */
    private Long storeId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 来源 {@link PickUpSourceEnum}
     */
    private Integer operateSource;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 已取货订单数据
     */
    private List<DeliveredOrder> deliveredOrderList;

    /**
     * SettlementLogDTO记录（不做参数）
     */
    private SettlementLogDTO settlementLog;

    /**
     * 是否操作了settlementLog（不做参数）
     */
    private Boolean operateSettlement;

    /**
     * 是否为定时任务
     */
    private Boolean isTask;

    /**
     * 单比订单强制找零汇总
     */
    private Integer forcedChangeSum;
}
