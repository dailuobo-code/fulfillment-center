package com.mallcai.fulfillment.api.msg;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Liu Yang
 * @description 对账统一对外消息体
 * @date: 2019-11-13 16:33
 */
@Data
public class UnifiedCompareMsg implements Serializable {

    private static final long serialVersionUID = -3954220797386996019L;

    /**
     * 对比类型
     * 1:冻品 2:生鲜分拣 3：生鲜采购
     */
    private Byte compareType;
    /**
     * 对比参数，JSON格式
     */
    private String compareParam;



}
