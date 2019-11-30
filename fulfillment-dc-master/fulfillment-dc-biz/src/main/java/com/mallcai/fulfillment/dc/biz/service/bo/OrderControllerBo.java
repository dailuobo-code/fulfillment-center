package com.mallcai.fulfillment.dc.biz.service.bo;

import lombok.Data;

/**
 * 订单条件实体
 * @author bh.zhong
 * @date 2019/11/5 4:46 PM
 */
@Data
public class OrderControllerBo {
    /**
     * 是否预售
     */
    private boolean isBooking;
    /**
     * 货品白名单
     */
    private boolean goodsGrayOpen;
    /**
     * 是否冻品
     */
    private boolean isFrozen;
    /**
     * 是否周期购
     */
    private boolean isPeriodBuy;
    /**
     * 是否标品
     */
    private boolean isStandard;
}
