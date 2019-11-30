package com.mallcai.fulfillment.biz.object.enums;

import lombok.Getter;

/**
 * @author: Liu Yang
 * @description 城市枚举
 * @date: 2019-11-19 17:08
 */
@Getter
public enum CityEnum {
    BENG_BU(27,"蚌埠"),
    HE_FEI(30,"合肥"),
    FU_YANG(31,"阜阳"),
    WU_HU(32,"芜湖"),
    AN_QING(33,"安庆"),
    ZHEN_JIANG(63,"镇江"),
    HUAI_AN(65,"淮安"),
    CHU_ZHOU(92,"滁州"),
    WU_HAN(121,"武汉"),
    HUAI_NAN(153,"淮南"),
    HUAI_BEI(156,"淮北"),
    ZHENG_ZHOU(171,"郑州"),
    LU_AN(203,"六安"),
    NAN_JING(220,"南京"),
    XU_ZHOU(221,"徐州"),
    TONG_LING(242,"铜陵"),
    YANG_ZHOU(252,"扬州"),
    CHANG_ZHOU(254,"常州"),
    MA_AN_SHAN(265,"马鞍山");

    private Integer cityId;
    private String cityName;


    CityEnum(Integer cityId,String cityName){
        this.cityId=cityId;
        this.cityName=cityName;
    }

    public static CityEnum fromCityId(Integer cityId){
        for(CityEnum cityEnum:CityEnum.values()){
            if(cityEnum.getCityId().equals(cityId)){
                return cityEnum;
            }
        }
        return null;
    }
}
