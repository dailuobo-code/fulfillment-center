package com.mallcai.manager.common.constant;

public class Constants {

    public static final String CP_NO_KEY="fulfillment:checkno";

    public static final String TBL_LEFT_V="left";

    public static final String TBL_RIGHT_V="right";

    public static final String TBL_CHECK_RESULT="result";

    /**
     * 最大查询时长
     */
    public static final Integer MAX_QUERY_TIME=5;
    /**
     * redis锁key前缀
     */
    public static final String REDIS_LOCK_PREFIX = "redis_lock_fulfillment_cp:";

    public static final String REDIS_LOCK_KEY_TRADE_COMPARE="trade:compare";

    public static final String REDIS_TRADE_SUCCESS_TIME="fulfillment_cp:trade:success:time";

    public static final String REDIS_ERP_WMS_LAST_TIME="fulfillment_cp:erp:wms:last:time";

    public static final String MDC_TRACE_ID = "traceId";
    /**
     * redis失效时间 默认一天 86400秒
     */
    public static final Integer REDIS_EXPIRE_TIME=86400;
    /**
     * 对账统一对外消息TOPIC
     */
    public static final String CP_UNIFIED_COMPARE_TOPIC="Fulfillment_UnifiedCompare_N";
    /**
     * 查询索引是是否覆盖所有城市，如果不是所有城市，则用逗号将城市ID分开
     */
    public final static String ALL_CITY="all";
    /**
     * 查询参数
     */
    public final static String PARAM_CITY="cities";
    public final static String PARAM_GROUP_VALUE="groupValue";


    public static final String PARAM_ORDER_TYPE = "orderType";
}
