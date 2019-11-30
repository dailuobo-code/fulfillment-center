package com.mallcai.fulfillment.pe.biz.service.enums;

import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import lombok.Getter;

import java.util.Date;

/**
 * @description: 推单日期类型枚举
 * @author: chentao
 * @create: 2019-08-23 21:14:07
 */
@Getter
public enum OrderPushDateEnum {

    TODAY("t", "当天"),
    T_1("t+1", "t+1日"),
    T_2("t+2", "t+2日"),
    T_3("t+3", "t+3日"),
    T_4("t+4", "t+4日");

    private String key;

    private String desc;

    OrderPushDateEnum (String key, String desc){

        this.key = key;
        this.desc = desc;
    }

    public static OrderPushDateEnum getEnuFromKey(String key){

        for (OrderPushDateEnum enu : OrderPushDateEnum.values()){

            if (enu.getKey().equals(key)){

                return enu;
            }
        }

        throw new BizException();
    }

    public static Date getOrderPushDate(String key, Date payTime){

        OrderPushDateEnum enu = getEnuFromKey(key);
        Date orderPushDate = null;
        switch (enu){

            case TODAY:

                orderPushDate = DateUtil.getDayStartIntervalToSomeDay(payTime, 0);
                break;

            case T_1:

                orderPushDate = DateUtil.getDayStartIntervalToSomeDay(payTime, 1);
                break;
            case T_2:
                orderPushDate = DateUtil.getDayStartIntervalToSomeDay(payTime, 2);
                break;

            case T_3:
                orderPushDate = DateUtil.getDayStartIntervalToSomeDay(payTime, 3);
                break;

            case T_4:

                orderPushDate = DateUtil.getDayStartIntervalToSomeDay(payTime, 4);

            default:
                break;
        }

        return orderPushDate;
    }
}
