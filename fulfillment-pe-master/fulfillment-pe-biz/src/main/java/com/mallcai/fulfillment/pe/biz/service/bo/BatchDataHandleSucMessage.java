package com.mallcai.fulfillment.pe.biz.service.bo;

import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description: 切割数据消息体
 * @author: chentao
 * @create: 2019-10-08 22:28:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDataHandleSucMessage {

    /**
     * 批次号,用于标记处理的批次
     */
    private String batchId;

    /**
     * 数据类型
     */
    private BatchDataTypeEnum batchDataTypeEnum;

    /**
     *  父批处理任务ID，多个批处理任务需要串联的场景
     */
    private List<String> parentBatchTaskIds;

    /**
     * 需要携带的参数，json格式
     */
    private String param;
}
