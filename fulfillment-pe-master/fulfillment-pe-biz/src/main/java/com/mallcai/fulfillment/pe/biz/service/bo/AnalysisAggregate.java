package com.mallcai.fulfillment.pe.biz.service.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisAggregate {
    private String groupValue;  //  分组维度

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date pushTime;  //  推仓时间

    private List<Integer> cityIds;   // 城市ID
    private List<Integer> storeIds;
    private Integer stauts;   // 状态

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startTime;   // 开始时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endTime;     // 结束时间
}
