package com.mallcai.fulfillment.pe.common.constants;

/**
 * 常量
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class Constants {


    /**
     * 发送生产订单TOPIC
     */
    public static final String ERP_TOPIC = "Product_Order_N";

    /**
     * 发送生产订单TOPIC
     */
    public static final String PURCHASE_TOPIC = "FulfillmentPe_Purchase_N";
    /**
     * 发送生产订单TOPIC
     */
    public static final String PURCHASE_RETRY_TOPIC = "FulfillmentPe_PurchaseCompensate_N";

    /**
     * 逗号
     */
    public static final String SYMBOL_COMMA = ",";

    /**
     * 下划线
     */
    public static final String SYMBOL_UNDERLINE = "_";

    /**
     * redis锁key前缀
     */
    public static final String REDIS_LOCK_PREFIX = "redis_lock_fulfillment_pe:";

    /**
     * redis推单任务锁key
     */
    public static final String REDIS_LOCK_KEY_ORDER_PUSH = "order:push";

    /**
     * redis生鲜是否下发任务锁key
     */
    public static final String REDIS_LOCK_KEY_FRESH_DATA_CHECK = "fresh:data:check";

    /**
     * redis标品积单任务锁key
     */
    public static final String REDIS_LOCK_KEY_STANDARD_ORDER_AGGREGATE = "standard:order:aggregate";

    /**
     * redis冻品积单任务锁key
     */
    public static final String REDIS_LOCK_KEY_FROZEN_ORDER_AGGREGATE = "frozen:order:aggregate";

    /**
     * 后台触发集单任务锁key
     */
    public static final String REDIS_LOCK_KEY_BACKGROUND_ORDER_AGGREGATE = "background:order:aggregate";

    public static final String MDC_TRACE_ID = "traceId";

    /**
     * redis采购锁key(包括采购和补采)
     */
    public static final String REDIS_LOCK_KEY_PURCHASE_PREDICT = "purchase:predict";

    public interface Rule{
        public static final String NAMESPACE = "a0c02ec0-f932-4a1c-b06f-777591d0874b";  // app_fulfillment 的命名空间
        public static final String GROUPNAME= "busi_rule";
        public static final String RULETYPE= "JSON";  // TEXT,JSON,YAML,Properties
    }

    /**
     * 删除状态 0：未删除 1：已删除
     */
    public static final Byte IS_DELETED=1;
    public static final Byte NOT_DELETED=0;
    public static final Byte ORDER_IS_DELETED=-1;

    /**
     * 补采预测
     */
    public static final Byte PREDICT_T=1;
    /**
     * T2 采购预测
     */
    public static final Byte PREDICT_T_2=2;

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
     * 检查类型：分拣
     */
    public static final Integer CHECK_TYPE_SORTING_FRESH=1;
    /**
     * 检查类型：采购
     */
    public static final Integer CHECK_TYPE_PURCHASE_FRESH=2;
    /**
     * 采购的品类：水果
     */
    public static final Integer CHECK_CATEGORY_FRUIT=1;
    /**
     * 采购的品类：蔬菜
     */
    public static final Integer CHECK_CATEGORY_VEGETABLE=2;

    /**
     * 生成文件名称的key
     */
    public static final String XLS_FILENAME_KEY="fulfillment:id:xsl";


    public static final Integer THRITY_DAYS_TTL = 86400 * 30;


}
