package com.mallcai.fulfillment.pe.dependency.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 发货仓类型枚举
 * @author: chentao
 * @create: 2019-08-26 18:57:10
 */
@Getter
public enum WareHouseTypeEnum {

    WARE_HOUSE_FRESH(1, "生鲜仓"),
    WARE_HOUSE_STANDARD(2, "标仓"),
    WARE_HOUSE_VIRTUAL(3, "虚拟");

    private Integer type;

    private String desc;

    WareHouseTypeEnum(Integer type, String desc){

        this.type = type;
        this.desc = desc;
    }

    public static WareHouseTypeEnum  explainType(String groupValue){
        if (StringUtils.equals("1",groupValue)){
                return WARE_HOUSE_STANDARD;
        }else if(StringUtils.equals("2",groupValue)){
              return  WARE_HOUSE_FRESH;
        }else if(StringUtils.equals("3",groupValue)){
            return  WARE_HOUSE_FRESH;
        }
        return null;
    }
}
