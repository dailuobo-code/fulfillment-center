package com.mallcai.fulfillment.biz.object.enums;

import com.google.common.base.Objects;
import lombok.Getter;

/**
 * 订单商品条目扩展信息
 *
 * @author bh.zhong
 * @date 2019/11/5 2:59 PM
 */
@Getter
public enum GroupValueEnum {

    ORDER_TYPE_STANDARD("1", 1, null, "标品"),
    STORE_FROZEN_PRODUCT("2", 2,1, "冻品"),
    FRESH_PRODUCT("3", 2,null, "生鲜");

    private String groupValue;

    /**
     * 商品类型，1:标品；2：非标品
     */
    private Integer categoryType;

    /**
     * 商品属性，1:冷藏
     */
    private Integer attributeType;


    private String desc;

    GroupValueEnum(String groupValue, Integer categoryType, Integer attributeType,
                   String desc){
        this.groupValue = groupValue;
        this.categoryType = categoryType;
        this.attributeType = attributeType;
        this.desc = desc;
    }

  public static GroupValueEnum fromCategoryTypeAndAttributeType(Integer categoryType,Integer attributeType){

      for (GroupValueEnum groupValueEnum : GroupValueEnum.values()){

          if (Objects.equal(groupValueEnum.getCategoryType(),categoryType) && Objects.equal(groupValueEnum.getAttributeType(),attributeType)){

              return groupValueEnum;
          }
      }
    return null;

  }
}
