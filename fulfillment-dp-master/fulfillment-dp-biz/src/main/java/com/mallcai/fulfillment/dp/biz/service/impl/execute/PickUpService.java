package com.mallcai.fulfillment.dp.biz.service.impl.execute;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.StandardResult;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.common.exception.BizException;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderSortingLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.PickupLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderSortingLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.PickupLogDO;
import com.mallcai.fulfillment.dp.api.common.dto.SettlementLogDTO;
import com.mallcai.fulfillment.dp.api.enums.SettlementStatusEnum;
import com.mallcai.fulfillment.dp.api.enums.SettlementTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.service.api.ICityGlobalService;
import com.mallcai.service.user.api.IInviteService;
import com.mallcai.service.user.api.IUserService;
import com.mallcai.service.user.vo.SOAInvite;
import com.mallcai.service.user.vo.user.AppUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.mallcai.ump.mgr.api.coupon.dto.request.SendUserCouponRequest;
import org.mallcai.ump.mgr.api.coupon.service.IUserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author yl
 * @description 计算后处理
 * @date 2019-07-18
 */
@Slf4j
@Service
public class PickUpService {

    @Resource
    private PickupLogDAO pickupLogDAO;
    @Resource
    private IInviteService iInviteService;
    @Resource
    private SettlementService settlementService;
    @Resource
    private OrderSortingLogDAO orderSortingLogDAO;
    @Autowired
    @Qualifier("caicaiTransactionTemplate")
    private TransactionTemplate caicaiTransactionTemplate;
    @Autowired
    @Qualifier("tradeTransactionTemplate")
    private TransactionTemplate tradeTransactionTemplate;
    @Resource
    private IUserService iUserService;
    @Resource
    private ICityGlobalService iCityGlobalService;
    @Resource
    private IUserCouponService iUserCouponService;
    @ConfigValue(key = "/global/-1/NoSendEggCouponPhone")
    private String noSendEggCouponPhone;

    /**
     * 保存入库记录
     */
    public boolean savePickupLog(DeliveredDetailDTO deliveredDetail,
                                 List<OrderSortingLogDO> sortingList) {

        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();
        boolean executeFlag = SettlementTypeEnum.PICK_UP_LOG.getCode().equals(settlementLog.getType())
                && SettlementStatusEnum.PROCESSING.getCode().equals(settlementLog.getStatus());
        if (!executeFlag) {
            if (!BooleanUtils.toBoolean(deliveredDetail.getOperateSettlement())
                    && !BooleanUtils.toBoolean(deliveredDetail.getIsTask())) {
                settlementService.insertWarnLog(deliveredDetail, SettlementTypeEnum.PICK_UP_LOG.getCode());
            }
            return true;
        }
        return caicaiTransactionTemplate.execute(caicaiTransactionStatus -> {
            PickupLogDO pickupLogDO = new PickupLogDO();
            pickupLogDO.setCityId(deliveredDetail.getCityId());
            pickupLogDO.setStoreId(deliveredDetail.getStoreId());
            pickupLogDO.setOperatorId(deliveredDetail.getOperatorId());
            pickupLogDO.setAppUserId(deliveredDetail.getUserId());
            pickupLogDO.setPickupDate(new Date());
            pickupLogDO.setPickupJson(JSONObject.toJSONString(deliveredDetail));
            pickupLogDAO.insertSelective(pickupLogDO);

            boolean tradeFlag = tradeTransactionTemplate.execute(tradeTransactionStatus -> {
                Integer type = SettlementTypeEnum.SORTING_LOG.getCode();
                Integer status = SettlementStatusEnum.PROCESSING.getCode();
                if (CollectionUtils.isEmpty(sortingList)) {
                    type = SettlementTypeEnum.ORDER_DETAIL_JS.getCode();
                    status = SettlementStatusEnum.PROCESSING.getCode();
                }
                return settlementService.updateSettlement(orderId, type, status, deliveredDetail);
            });
            if (!tradeFlag) {
                throw new BizException("更新settlement失败");
            }
            deliveredDetail.setOperateSettlement(true);
            return true;
        });
    }


    /**
     * 给取货的邀请人发券 该方案为临时方案，创建多个券模板，按城市获取全部的券模板，给用户依次发放券
     *
     * @param invitedUserId 被邀请人用户id，取货人
     */
    public void sendEggCouponForInviteUser(Integer invitedUserId) {
        SOAInvite soaInvite = iInviteService.getByInvitedUserIdFromCache(invitedUserId);
        if (soaInvite == null) {
            log.info("鸡蛋券不需要发送 userId:" + invitedUserId);
            return;
        }
        if (soaInvite.getActiveStatus() != null && soaInvite.getActiveStatus() == 1) {
            try {
                StandardResult activeResult = iInviteService.activeInvite(soaInvite.getId());
                if (!activeResult.getSuccess()) {
                    log.warn(activeResult.getErrorMsg());
                }
            } catch (Exception e) {
                log.error("激活邀请关系异常 被邀请人：" + invitedUserId, e);
            }
        }

        if (!isNeedSend(soaInvite)) {
            log.info("鸡蛋券不需要发送 userId:" + invitedUserId);
            return;
        }
        Integer inviteUserId = soaInvite.getInviteUserId();
        AppUserInfo appUserInfo = iUserService.getUserInfoById(inviteUserId);
        if (appUserInfo == null) {
            log.warn("邀请人用户不存在 userId:" + invitedUserId);
            return;
        }

        if (StringUtils.isBlank(noSendEggCouponPhone)) {
            log.error("获取配置中心鸡蛋券异常，noSendEggCouponPhone:{}", noSendEggCouponPhone);
        } else {
            List<String> eggCouponPhones = Arrays.asList(noSendEggCouponPhone.split(","));
            log.info("被过滤的鸡蛋券用户，eggCouponPhones:{}", eggCouponPhones);
            //某些账号不给发券
            if (eggCouponPhones.contains(appUserInfo.getPhone())) {
                log.info("邀请人用户为不发券用户:" + appUserInfo.getPhone());
                return;
            }
        }
        SendUserCouponRequest sendUserCouponRequest = new SendUserCouponRequest();
        sendUserCouponRequest.setPhone(appUserInfo.getPhone());
        sendUserCouponRequest.setUserId(appUserInfo.getUserId());
        sendUserCouponRequest.setCityId(appUserInfo.getCityId());
        try {
            PlainResult<Void> result = iUserCouponService.sendInviteCouponEntry(sendUserCouponRequest);
            if (result.isSuccess()) {
                updateRel(soaInvite);
            }
        } catch (Exception e) {
            log.error("取货发券失败", e);

        }
    }

    private boolean isNeedSend(SOAInvite actInvite) {
        // 邀请人的
        Byte inviteFlag = actInvite.getInviteFlag();
        return inviteFlag == SOAInvite.FLAG_UN_SEND;
    }

    /**
     * 更新关系状态为已领取 并且删除缓存
     */
    private void updateRel(SOAInvite soaInvite) {
        try {
            soaInvite.setInviteFlag(SOAInvite.FLAG_SEND_ED);
            iInviteService.update(soaInvite);
        } catch (Exception e) {
            log.error("##########updateRel更新邀请状态失败soaInvite：" + JSONObject.toJSONString(soaInvite), e);
        }
    }

    /**
     * 保存分拣记录
     */
    public boolean saveOrderSortingLogDAO(List<OrderSortingLogDO> sortingList,
                                          DeliveredDetailDTO deliveredDetail) {

        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();
        boolean executeFlag = SettlementTypeEnum.SORTING_LOG.getCode().equals(settlementLog.getType())
                && SettlementStatusEnum.PROCESSING.getCode().equals(settlementLog.getStatus());
        if (!executeFlag || CollectionUtils.isEmpty(sortingList)) {
            if (!BooleanUtils.toBoolean(deliveredDetail.getOperateSettlement())
                    && !BooleanUtils.toBoolean(deliveredDetail.getIsTask())) {
                settlementService.insertWarnLog(deliveredDetail, SettlementTypeEnum.SORTING_LOG.getCode());
            }
            return true;
        }

        return caicaiTransactionTemplate.execute(caicaiStatus -> {
            orderSortingLogDAO.insertSelective4List(sortingList);

            Integer type = SettlementTypeEnum.ORDER_DETAIL_JS.getCode();
            Integer status = SettlementStatusEnum.PROCESSING.getCode();
            boolean tradeFlag = tradeTransactionTemplate.execute(
                    tradeStatus -> settlementService
                            .updateSettlement(orderId, type, status, deliveredDetail));
            if (!tradeFlag) {
                throw new BizException("更新settlement 失败");
            }
            deliveredDetail.setOperateSettlement(true);
            return true;
        });
    }
}
