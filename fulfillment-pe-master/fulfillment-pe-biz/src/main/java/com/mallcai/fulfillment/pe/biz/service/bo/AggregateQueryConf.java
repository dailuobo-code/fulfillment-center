package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AggregateQueryConf {
    private String taskTag;    // 任务标记
    private String groupType;  //  分组维度
    private String groupValue; // 维度值
    private List<Integer> stores;   // 门店集合
    private List<Integer> warehouses;  // 仓库集合
    private List<Integer> cities;    // 城市集合
    private List<String> goodes;       // 货品集合
    private Boolean distFlag;     //是否采用分布式功能
    private Integer page;
    private Integer size;
}
