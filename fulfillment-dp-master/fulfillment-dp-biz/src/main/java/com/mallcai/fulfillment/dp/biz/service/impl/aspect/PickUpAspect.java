package com.mallcai.fulfillment.dp.biz.service.impl.aspect;


import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.common.constants.SymbolConstants;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.common.utils.RedisKeyGenerator;
import com.mallcai.fulfillment.dp.api.enums.PickUpSourceEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.fulfillment.dp.api.request.OrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.OrderRelationQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yl
 * @description 取菜系统控制处理
 * @date 2019-07-20
 */
@Slf4j
@Aspect
@Component
public class PickUpAspect {

    @Resource
    private JedisClientUtil jedisClientUtil;
    private static final String REGULAR = "(true|false)(:)[0-9]+";
    private static final String FALSE = "false";
    private static final Integer ALL = 0;

    /**
     * 取菜系统控制处理
     */
    @Around(("(execution(* com.mallcai.fulfillment.dp.api.DpOrderQueryService.queryBatchOrderList(..)) || execution(* com.mallcai.fulfillment.dp.api.DpOrderQueryService.queryOrderAndLock(..)) || execution(* com.mallcai.fulfillment.dp.api.DpOrderOperateService.batchSyncDeliveredOrder(..)) || execution(* com.mallcai.fulfillment.dp.api.DpOrderOperateService.syncDeliveredOrder(..)))"))
    public PlainResult pickUpControl(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>>>>>>>>>取菜系统控制切面执行开始:method:>>>{}", joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        Integer operateSource = null;
        if (args[0] instanceof OrderQueryDTO) {
            OrderQueryDTO orderQueryDTO = (OrderQueryDTO) args[0];
            operateSource = orderQueryDTO.getOperateSource();
        }
        if (args[0] instanceof OrderRelationQueryDTO) {
            OrderRelationQueryDTO orderRelationQueryDTO = (OrderRelationQueryDTO) args[0];
            operateSource = orderRelationQueryDTO.getOperateSource();
        }
        if (args[0] instanceof DeliveredDetailDTO) {
            DeliveredDetailDTO detailDTO = (DeliveredDetailDTO) args[0];
            operateSource = detailDTO.getOperateSource();
        }
        if (args[0] instanceof List) {
            List detailList = (List) args[0];
            DeliveredDetailDTO detailDTO = (DeliveredDetailDTO) (detailList.get(0));
            operateSource = detailDTO.getOperateSource();
        }
        String switchResult = jedisClientUtil.get(RedisKeyGenerator.onlinePickUpSwitchKey());

        if (StringUtils.isBlank(switchResult)
                || !switchResult.matches(REGULAR)) {
            return (PlainResult) joinPoint.proceed();
        }

        String[] result = switchResult.split(SymbolConstants.COLON);
        boolean endFlag = FALSE.equals(result[0])
                && (Integer.valueOf(result[1]).equals(ALL)
                || !PickUpSourceEnum.isContainType(operateSource)
                || Integer.valueOf(result[1]).equals(operateSource));
        if (endFlag) {
            //不执行
            log.warn("取菜系统控制关闭switch:{},operateSource:{}", switchResult, operateSource);
            Thread.sleep(5000L);
            return PlainResultBuilder.fail(Errors.FULFILLMENT_SYSTEM_ERROR.getCode(), "取菜系统控制关闭");
        }
        return (PlainResult) joinPoint.proceed();
    }

}
