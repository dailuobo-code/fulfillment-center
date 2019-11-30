package com.mallcai.fulfillment.pe.biz.service.bo;

import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description: 大数据处理任务
 * @author: chentao
 * @create: 2019-10-13 23:56:29
 */
@Data
public class BatchDataTaskMessage {

    /**
     * 批处理任务ID，全局唯一
     */
    private String batchTaskId;

    /**
     * 单条任务唯一标识
     */
    private List<Object> uniqueIds;

    /**
     * 批任务分页大小
     */
    private Integer splitCount;

    /**
     * 任务数据类型
     */
    private BatchDataTypeEnum batchDataTypeEnum;

    /**
     *  父批处理任务ID，多个批处理任务需要串联的场景
     */
    private List<String> parentBatchTaskIds;

    /**
     * 业务需要携带的参数,json格式
     */
    private String param;
}
