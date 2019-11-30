package com.mallcai.fulfillment.dc.biz.service.bo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class PeriodBuyConfig {

    private List<String> cities;

    private Integer peroid;

    private Map<String,String> prodMap;

    /**
     * 配置描述信息
     */
    private String desc;
}
