package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 货品计算值
 * @author bh.zhong
 * @date 2019/10/13 10:42 PM
 */
@Data
public class GoodsCalculateBO {
    /**
     * 由哪些id集合成的采购货品
     */
    List<Long> ids;
    /** 货品编号 **/
    private String goodsId;
    /** 货品计量单位 **/
    private String goodsUnit;
    /** 货品总数量 **/
    private BigDecimal goodsTotalAmount;
}
