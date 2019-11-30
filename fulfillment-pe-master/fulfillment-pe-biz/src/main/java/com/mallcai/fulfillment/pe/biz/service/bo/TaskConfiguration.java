package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-09-18 00:46:21
 */
@Data
public class TaskConfiguration {

    /**
     * 任务名，不可重复
     */
    private String taskName;

    private String cron;

    private List<ConfigurationCondition> conditions;
}
