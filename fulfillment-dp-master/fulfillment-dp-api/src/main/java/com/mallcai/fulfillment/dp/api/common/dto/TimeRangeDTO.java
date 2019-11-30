package com.mallcai.fulfillment.dp.api.common.dto;

import com.mallcai.fulfillment.dp.api.enums.TimeTypeEnum;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yl
 * @description 时间dto
 * @date 2019-07-15
 */
@Data
public class TimeRangeDTO implements Serializable {

    private static final long serialVersionUID = -1961954447124163219L;
    /**
     * 时间类型 {@link TimeTypeEnum}
     */
    private Integer timeType;

    /**
     * 指定时间天数
     */
    private Integer appointDay;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;
}
