package com.mallcai.fulfillment.biz.object.enums;

import lombok.Getter;

/**
 * @description: 商品扩展信息枚举
 * @author: chentao
 * @create: 2019-08-29 11:04:17
 */
@Getter
public enum CommodityInfoExtEnum {

    SPEC_ID("specId", "规格ID"),
    PRODUCT_NUM("productNum", "商品数量"),
    PRODUCT_NO("productNo", "商品总部编码"),
    PACKAGE_QUANTITY("packageQuantity", "商品每份数量"),
    IS_GROUP_BUYING("isGroupBuying", "商品是否是社区团购，1:是;0:否"),
    GOODS_ID("goodsId","货品编号"),
    REL_NUM("relNum","货品关联数量"),
    GOODS_UNIT("goodsUnit","货品计量单位");

    private String key;

    private String desc;

    CommodityInfoExtEnum(String key, String desc){

        this.key = key;
        this.desc = desc;
    }
}
