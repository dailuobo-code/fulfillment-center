package com.mallcai.fulfillment.dp.biz.service.impl.task;


import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.common.utils.DateTimeUtils;
import com.mallcai.fulfillment.common.utils.RedisKeyGenerator;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dao.SettlementLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject.SettlementLogDO;
import com.mallcai.fulfillment.dp.infrastructure.redis.DistributeLockTemplate;
import com.mallcai.fulfillment.dp.api.DpOrderOperateService;
import com.mallcai.fulfillment.dp.api.enums.SettlementTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 取货定时任务
 *
 * @author yl
 * @date 2019/07/20
 */
@Slf4j
@Service
public class PickUpTask {

    @Resource
    private DpOrderOperateService dpOrderOperateService;

    @Resource
    private JedisClientUtil jedisClientUtil;

    @Resource
    private DistributeLockTemplate distributeLockTemplate;

    @Resource
    private SettlementLogDAO settlementLogDAO;

    @Value("${pickup.timer.switch}")
    private String pickupTimerSwitch;

    private static final long LOCK_EXPIRETIME = 2 * 60 * 60 * 1000L;

    public void run() {
        try {
            log.info("取货定时任务开始");
            distributeLockTemplate.doWithLock(RedisKeyGenerator.orderPickUpLockKey(), LOCK_EXPIRETIME, 1,
                    this::handleTask);
            log.info("取货定时任务结束");
        } catch (Exception e) {
            log.error("取货定时任务 异常信息={}", e.getMessage(), e);
        }
    }

    private boolean handleTask() {
        Date startDate = DateTimeUtils.addDate(new Date(), -14);
        List<SettlementLogDO> settlementLogDoList = settlementLogDAO.selectByType(
                Arrays.asList(SettlementTypeEnum.PICK_UP_LOG.getCode(),
                        SettlementTypeEnum.SORTING_LOG.getCode(),
                        SettlementTypeEnum.ORDER_DETAIL_JS.getCode(),
                        SettlementTypeEnum.CHANGE_UPDATE.getCode()), startDate);

        if (CollectionUtils.isEmpty(settlementLogDoList)) {
            return true;
        }
        for (SettlementLogDO settlementLogDO : settlementLogDoList) {
            try {
                if (!getSwitch()) {
                    log.info("取货定时任务开始 中止,key:{}", RedisKeyGenerator.orderPickUpSwitchKey());
                    return true;
                }

                DeliveredDetailDTO detailDTO = JSONObject.parseObject(settlementLogDO.getRequestData(),
                        DeliveredDetailDTO.class);
                detailDTO.setIsTask(true);
                dpOrderOperateService.syncDeliveredOrder(detailDTO);
            } catch (Exception e) {
                log.error("settlementLogDO错误:" + JSONObject.toJSONString(settlementLogDO), e);
            }
        }
        return true;
    }

    private boolean getSwitch() {
        return !StringUtils.isEmpty(pickupTimerSwitch) && "1".equals(pickupTimerSwitch);
    }
}
