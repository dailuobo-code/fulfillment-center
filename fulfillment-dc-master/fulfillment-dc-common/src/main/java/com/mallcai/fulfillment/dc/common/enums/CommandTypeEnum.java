package com.mallcai.fulfillment.dc.common.enums;

import com.mallcai.fulfillment.dc.common.exception.BizException;
import lombok.Getter;

/**
 * @description: 指令类型枚举
 * @author: chentao
 * @create: 2019-11-14 00:32:20
 */
@Getter
public enum CommandTypeEnum {

    TMS_BOOKING_CAR_18("tmsBookingCar", "18点排车预定指令"),
    TMS_ADJUST_CAR("tmsAdjustCar", "20点调整指令");

    private String type;

    private String desc;

    CommandTypeEnum(String type, String desc){

        this.type = type;
        this.desc = desc;
    }

    public static CommandTypeEnum fromType(String type){

        for (CommandTypeEnum commandTypeEnum : CommandTypeEnum.values()){

            if (commandTypeEnum.type.equals(type)){

                return commandTypeEnum;
            }
        }

        throw new BizException();
    }
}
