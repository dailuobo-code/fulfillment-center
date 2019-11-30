package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * 订单商品条目扩展信息
 *
 * @author bh.zhong
 * @date 2019/11/5 2:59 PM
 */
@Getter
public enum OrderItemInfoExtEnum {

    SPEC_ID("specId", "规格ID"),
    PRODUCT_NUM("productNum", "商品数量"),
    PRODUCT_NO("productNo", "商品总部编码"),
    PACKAGE_QUANTITY("packageQuantity", "商品每份数量"),
    IS_GROUP_BUYING("isGroupBuying", "商品是否是社区团购，1:是;0:否"),
    GOODS_ID("goodsId","货品编号"),
    REL_NUM("relNum","货品关联数量"),
    GOODS_UNIT("goodsUnit","货品计量单位"),
    GROUP_VALUE("groupValue","转换类型：1.标品 2.冻品 3.生鲜");

    private String key;

    private String desc;

    OrderItemInfoExtEnum(String key, String desc){

        this.key = key;
        this.desc = desc;
    }
}
