package com.mallcai.fulfillment.dp.biz.service.impl.verify;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.enums.TimeTypeEnum;
import com.mallcai.fulfillment.dp.api.request.MilkPickUpDTO;
import com.mallcai.fulfillment.dp.api.request.MilkQueryDTO;
import com.mallcai.fulfillment.dp.api.response.MilkResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 牛奶参数校验
 * @author zhanghao
 * @date 2019-08-15 20:37:51
 */
@Component
@Slf4j
public class MilkParamChecker implements Serializable {
    private static final long serialVersionUID = -630240881634904231L;

    /**
     * 关联订单查询参数校验
     */
    public PlainResult<MilkResultDTO> checkMilkQuery(MilkQueryDTO milkQueryDTO){
        if (Objects.isNull(milkQueryDTO)) {
            log.warn("【牛奶取货列表查询】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        boolean cityAndStoreFlag = Objects.isNull(milkQueryDTO.getCityId()) || Objects.isNull(milkQueryDTO.getStoreId());
        if (cityAndStoreFlag) {
            log.warn("【牛奶取货列表查询】缺少城市和门店信息，orderRelationQuery:{}", JSONObject.toJSONString(milkQueryDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "cityId或storeId为空");
        }
        if (!TimeTypeEnum.isContainType(milkQueryDTO.getTimeType())) {
            log.warn("【牛奶取货列表查询】 TimeType不匹配，{}", JSONObject.toJSONString(milkQueryDTO));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "timeType不匹配");
        }
        if (Objects.isNull(milkQueryDTO.getAppointDay())) {
            if (Objects.isNull(milkQueryDTO.getBeginTime())|| Objects.isNull(milkQueryDTO.getEndTime())) {
                log.warn("【牛奶取货列表查询】 时间区间参数错误，{}", JSONObject.toJSONString(milkQueryDTO));
                return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "时间区间参数错误");
            }
            if (milkQueryDTO.getBeginTime().getTime() > milkQueryDTO.getEndTime().getTime()) {
                log.warn("【牛奶取货列表查询】 开始时间大于结束时间，{}", JSONObject.toJSONString(milkQueryDTO));
                return PlainResultBuilder
                        .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "开始时间大于结束时间");
            }
        }
        return PlainResultBuilder.success(null);
    }


    public PlainResult<String> checkMilkPickUp(MilkPickUpDTO milkPickUpDTO){
        if (Objects.isNull(milkPickUpDTO)) {
            log.warn("【牛奶取货】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        boolean flag = Objects.isNull(milkPickUpDTO.getId()) || Objects.isNull(milkPickUpDTO.getUserId())  || Objects.isNull(milkPickUpDTO.getReturnNum());
        if (flag) {
            log.warn("【牛奶取货】缺少字段信息，orderRelationQuery:{}", JSONObject.toJSONString(milkPickUpDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "ID,userId,renturnNum不能为空");
        }
        return PlainResultBuilder.success(null);
    }
}
