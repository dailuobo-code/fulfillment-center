package com.mallcai.fulfillment.pe.biz.service.constants;

/**
 * @description: 业务常量类
 * @author: chentao
 * @create: 2019-10-10 01:32:09
 */
public class BizConstant {

    /**
     *
     */
    public static final String AGGREGATE_ORDER_SPIT_DATA_KEY_FORMAT = "filfullment:split:data:aggregate:order_%s_%s_%s";

    public static final String AGGREGATE_ORDER_BATCH_ALL_KEY_FORMAT = "filfullment_batch_data_aggregate_order_%s";

    /**
     * 一天秒数
     */
    public static final Integer ONE_DAY_SECS = 24 * 60 * 60;

    /**
     * 批处理任务消息topic
     */
    public static final String BATCH_DATA_TASK_MSG_TOPIC = "Fulfillment_BatchDataTask_N";

    /**
     * 批处理分页任务消息topic
     */
    public static final String SPLIT_DATA_TASK_MSG_TOPIC = "Fulfillment_SplitDataTask_N";

    /**
     * 批处理成功消息topic
     */
    public static final String BATCH_DATA_TASK_HANDLE_SUC_TOPIC = "Fulfillment_BatchDataTaskSuc_N";

    /**
     * 开始集单消息topic
     */
    public static final String AGGREGATE_ORDER_START_TOPIC = "Fulfillment_AggregateOrderStart_N";

    /**
     * 集单与数据重入竞争锁key format，groupvalue + warehouseId
     */
    public static final String AGGREGATE_ORDER_DATA_RELOAD_LOCK_KEY_FORMAT = "pe_fulfillment_aggregate_order_data_reload_%s_%d";

    /**
     * 集单批处理数据分割大小
     */
    public static final Integer AGGREGATE_ORDER_BATCH_DATA_SPLIT_COUNT = 10000;
}
