package com.mallcai.fulfillment.pe.infrastructure.enums;

import com.google.common.base.Objects;
import lombok.Getter;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 分组值枚举，所有值不重复(方便后期扩展，只使用一个字段也可以
 *  定位属于哪个分组)
 * @author: chentao
 * @create: 2019-09-16 17:00:44
 */
@Getter
public enum GroupValueEnum {

    ORDER_TYPE_STANDARD("ORDER_TYPE_STANDARD", GroupTypeEnum.ORDER_TYPE.getGroupType(), "1", 1, null, null,true, "订单类型-标品"),
    STORE_FROZEN_PRODUCT("STORE_FROZEN_PRODUCT", GroupTypeEnum.STORE.getGroupType(), "2", 2,1, null, true, "存储类型-冻品"),
    FRESH_PRODUCT("FRESH_PRODUCT", GroupTypeEnum.CATEGORY.getGroupType(), "3", 2,null, null, true, "品类-生鲜");


    private String key;

    private Byte group;

    private String groupValue;

    /**
     * 商品类型，1:标品；2：非标品
     */
    private Integer categoryType;

    /**
     * 商品属性，1:冷藏
     */
    private Integer attributeType;

    /**
     * sku
     */
    private String sku;

    /**
     * 是否需要二级分组，默认：需要
     */
    private boolean isNeedSecondGroup;

    private String desc;

    GroupValueEnum(String key, Byte group, String groupValue, Integer categoryType, Integer attributeType,
                   String sku, boolean isNeedSecondGroup, String desc){

        this.key = key;
        this.group = group;
        this.groupValue = groupValue;
        this.categoryType = categoryType;
        this.attributeType = attributeType;
        this.sku = sku;
        this.isNeedSecondGroup = isNeedSecondGroup;
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

    public static GroupValueEnum fromValue(String value){

       for (GroupValueEnum groupValueEnum : GroupValueEnum.values()){

           if (groupValueEnum.getGroupValue().equals(value)){

               return groupValueEnum;
           }
       }

       return null;
    }

    public static GroupValueEnum fromKey(String key){

        for (GroupValueEnum groupValueEnum : GroupValueEnum.values()){

            if (groupValueEnum.getKey().equals(key)){

                return groupValueEnum;
            }
        }

        return null;
    }

    public static List<String> getAllGroupValue(){

        List<String> allGroupValue = new ArrayList<>();

        for (GroupValueEnum groupValueEnum : GroupValueEnum.values()){

            allGroupValue.add(groupValueEnum.getGroupValue());
        }

        return allGroupValue;
    }
}
