package com.mallcai.fulfillment.biz.object.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckConfigBo {

    /**
     * 业务类型
     */
    private String bizTag;

    /**
     * 业务参数
     */
    private Map<String,Object> bizParam;
}
