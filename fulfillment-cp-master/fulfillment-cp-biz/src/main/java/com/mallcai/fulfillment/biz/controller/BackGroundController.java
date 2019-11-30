package com.mallcai.fulfillment.biz.controller;

import com.google.common.collect.ImmutableMap;
import com.mallcai.fulfillment.biz.object.bo.CheckConfigBo;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.BizTagEnum;
import com.mallcai.fulfillment.biz.object.enums.OrderTypeEnum;
import com.mallcai.fulfillment.biz.service.*;
import com.mallcai.manager.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-11-08 20:25
 */
@Slf4j
@RestController
@RequestMapping(value = "/fulfillment_cp/background/")
public class BackGroundController {


    @Autowired
    private TaskConfigService taskConfigService;

    @Autowired
    private LoaderService loaderService;

    @Autowired
    private CheckService checkService;

    @Autowired
    private CheckRecordService checkRecordService;

    @Autowired
    private HandleService handleService;
    @Autowired
    private Environment environment;
    @Autowired
    private FulfillmentErpLoaderService fulfillmentErpLoaderService;

    private final long LOCK_EXPIRE_TIME = 10 * 60L;

    @RequestMapping(value = "order/compare")
    public String compareOrders(@RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("cities") String cities,@RequestParam("ifUpdateRedis") boolean ifUpdateRedis) {
        log.info("后门触发交易对账口子,beginTime:{},endTime:{},cities:{}", beginTime, endTime, cities);

        CheckConfigBo config = CheckConfigBo.builder()
                .bizTag("TRADE_COMPARE")
                .bizParam(ImmutableMap.of("cities", cities,
                        "interval", 3600 * 5))
                .build();

        log.info("开始执行对账任务[{}]，当前执行环境:{},对账参数:{}", config.getBizTag(), environment.getActiveProfiles()[0], config.getBizParam());

        //1. 构建context
        CheckContext checkContext = taskConfigService.buildContext(config);
        checkContext.setBeginTime(DateUtil.parseDateTime(beginTime));
        checkContext.setEndTime(DateUtil.parseDateTime(endTime));

        checkRecordService.initRecord(checkContext);

        //2. load  data
        loaderService.loadCheckDataAndProcess(checkContext);

        //3. check
        checkService.checkData(checkContext);
        //不需要更新redis
        checkContext.setIfNeedUpdateRedis(ifUpdateRedis);

        //4. record
        checkRecordService.dealRecord(checkContext);

        //5. handle result
        handleService.handle(checkContext);


        return "success";
    }

    @RequestMapping(value = "fulErp/compare")
    public String compareFreshSorting(@RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("cities") String cities,@RequestParam("orderType") Integer orderType) {
        log.info("后门触发交易对账口子,beginTime:{},endTime:{},cities:{}", beginTime, endTime, cities);

        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getEnuFromKey(orderType);

        BizTagEnum bizTagEnum = null;

        switch (orderTypeEnum) {
            case FROZEN:
                bizTagEnum = BizTagEnum.FROZEN_COMPARE;
                break;
            case FRESH:
                bizTagEnum = BizTagEnum.FRESH_SORTING_COMPARE;
                break;
            default:
                break;
        }

        if (bizTagEnum == null) {
            return "orderType error, must be 2 or 3";
        }

        CheckConfigBo config = CheckConfigBo.builder()
                .bizTag(bizTagEnum.getValue())
                .bizParam(ImmutableMap.of("cities", cities,"groupValue",String.valueOf(orderType))).build();

        log.info("开始执行对账任务[{}]，当前执行环境:{},对账参数:{}", config.getBizTag(), environment.getActiveProfiles()[0], config.getBizParam());

        //1. 构建context
        CheckContext checkContext = taskConfigService.buildContext(config);
        checkContext.setBeginTime(DateUtil.parseDateTime(beginTime));
        checkContext.setEndTime(DateUtil.parseDateTime(endTime));

        checkRecordService.initRecord(checkContext);

        //2. load  data
        fulfillmentErpLoaderService.loadCheckDataAndProcess(checkContext);

        //3. check
        checkService.checkData(checkContext);
        //不需要更新redis
        checkContext.setIfNeedUpdateRedis(false);

        //4. record
        checkRecordService.dealRecord(checkContext);

        //5. handle result
        handleService.handle(checkContext);


        return "success";
    }


    @RequestMapping(value = "erpwms/compare")
    public String compareErpWms(@RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime, @RequestParam("cities") String cities, @RequestParam("orderType") Integer orderType) {
        log.info("后门触发交易对账口子,beginTime:{},endTime:{},cities:{}, orderType:{}", beginTime, endTime, cities, orderType);

        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getEnuFromKey(orderType);

        BizTagEnum bizTagEnum = null;

        switch (orderTypeEnum) {
            case FROZEN:
                bizTagEnum = BizTagEnum.ERP_WMS_FROZEN_COMPARE;
                break;
            case FRESH:
                bizTagEnum = BizTagEnum.ERP_WMS_FRESH_COMPARE;
                break;
            default:
                break;
        }

        if (bizTagEnum == null) {
            return "orderType error, must be 2 or 3";
        }

        CheckConfigBo config = CheckConfigBo.builder()
                .bizTag(bizTagEnum.getValue())
                .bizParam(ImmutableMap.of("cities", cities,
                        "orderType", String.valueOf(orderType)))
                .build();

        log.info("开始执行对账任务[{}]，当前执行环境:{},对账参数:{}", config.getBizTag(), environment.getActiveProfiles()[0], config.getBizParam());

        //1. 构建context
        CheckContext checkContext = taskConfigService.buildContext(config);
        checkContext.setBeginTime(DateUtil.parseDateTime(beginTime));
        checkContext.setEndTime(DateUtil.parseDateTime(endTime));

        checkRecordService.initRecord(checkContext);

        //2. load  data
        loaderService.loadCheckDataAndProcess(checkContext);

        //3. check
        checkService.checkData(checkContext);
        //不需要更新redis
        checkContext.setIfNeedUpdateRedis(false);

        //4. record
        checkRecordService.dealRecord(checkContext);

        //5. handle result
        handleService.handle(checkContext);


        return "success";
    }

}
