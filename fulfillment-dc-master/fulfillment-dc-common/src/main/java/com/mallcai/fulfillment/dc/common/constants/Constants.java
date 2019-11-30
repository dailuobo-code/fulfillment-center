package com.mallcai.fulfillment.dc.common.constants;

/**
 * 常量
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class Constants {


    /**
     * 交易topic
     */
    public static final String TRADE_TOPIC = "TRADE_TOPIC";
    /**
     * 大数据采购预测TOPIC
     */
    public static final String PE_PURCHASE_FORECAST_TOPIC="Fulfillment_PurchaseForecastTopic_N";
    /**
     * 大数据采购预测TAGS
     */
    public static final String PE_PURCHASE_FORECAST_TAGS="pe_fulfillment_purchase_forecast";
    /**
     * 大数据补采预测TOPIC
     */
    public static final String PE_PURCHASE_ADD_FORECAST_TOPIC="Fulfillment_PurchaseAddForecast_N";
    /**
     * 大数据补采预测TAGS
     */
    public static final String PE_PURCHASE_ADD_FORECAST_TAGS="pe_fulfillment_purchase_add_forecast";
    /**
     * 大数据分拣预测TOPIC
     */
    public static final String PE_SORTING_FORECAST_TOPIC="Fulfillment_SortingForecast_N";
    /**
     * 大数据分拣预测TAGS
     */
    public static final String PE_SORTING_FORECAST_TAGS="pe_fulfillment_sorting_forecast";
    /**
     * 开始分拣解锁消息topic
     */
    public static final String SORTING_RELEASE_LOCK_TOPIC = "Fulfillment_AggregateOrderStart_N";

    /**
     * 实时销量topic
     */
    public static final String FULFILLMENT_REALTIMEORDERINFOTOPIC = "Fulfillment_RealTimeOrderInfoTopic_N";

    /**
     * 调度指令topic
     */
    public static final String DISPATCH_COMMAND_TOPIC = "Fulfillment_DispatchCommand_N";

    /**
     * 调度指令回执topic
     */
    public static final String DISPATCH_COMMAND_RECEIPT_TOPIC = "Fulfillment_DispatchCommandReceipt_N";

    /**
     * 货品生产单消息topic
     */
    public static final String PRODUCE_ORDER_GOODS_MSG_TOPIC = "Fulfillment_ProduceOrderGoods_N";

    /**
     * 删除状态 0：未删除 1：已删除
     */
    public static final Byte IS_DELETED=1;
    public static final Byte NOT_DELETED=0;

    /**
     * redis锁key前缀
     */
    public static final String REDIS_LOCK_PREFIX = "redis_lock_fulfillment_dc:";
    /**
     * redis采购预测锁key
     */
    public static final String REDIS_LOCK_KEY_PURCHASE_PREDICT_T_2 = "purchase:predict:t2:";
    /**
     * redis补采购预测锁key
     */
    public static final String REDIS_LOCK_KEY_PURCHASE_PREDICT_T = "purchase:predict:t:";
    /**
     * redis补采购预测锁key
     */
    public static final String REDIS_LOCK_KEY_SORTING_PREDICT = "purchase:sorting:predict:";
    /**
     * 补采预测
     */
    public static final Byte PREDICT_T=1;
    /**
     * T2 采购预测
     */
    public static final Byte PREDICT_T_2=2;
    /**
     * 交易订单ID的前缀（来自MGR导入）
     */
    public static final String MGR_PREFIX="mgr_";
    /**
     * 交易订单ID的前缀（来自预售订单）
     */
    public static final String BOOK_PREFIX="book_";
    /**
     * 交易订单ID的前缀（来自大数据分拣）
     */
    public static final String BIG_PREFIX="big_";

    public static final String NEED_PUSH = "1";

    /**
     * 逗号
     */
    public static final String SYMBOL_COMMA = ",";
}
