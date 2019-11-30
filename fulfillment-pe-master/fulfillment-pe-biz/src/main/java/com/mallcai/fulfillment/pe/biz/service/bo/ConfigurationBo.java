package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-09-18 00:34:15
 */
@Data
public class ConfigurationBo {

    private String configName;

    private String configValue;

    private List<ConfigurationCondition> conditions;
}
