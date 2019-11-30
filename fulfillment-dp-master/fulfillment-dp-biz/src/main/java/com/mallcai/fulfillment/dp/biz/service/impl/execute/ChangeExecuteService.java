package com.mallcai.fulfillment.dp.biz.service.impl.execute;

import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.StandardResult;
import com.mallcai.fulfillment.common.exception.BizException;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.PointRefundErrorLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.UserPickupChangeErrorLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.PointRefundErrorLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.UserPickupChangeErrorLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dao.SettlementLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject.SettlementLogDO;
import com.mallcai.fulfillment.dp.infrastructure.mongo.OrderMongoDAO;
import com.mallcai.fulfillment.dp.api.common.dto.SettlementLogDTO;
import com.mallcai.fulfillment.dp.api.enums.SettlementStatusEnum;
import com.mallcai.fulfillment.dp.api.enums.SettlementTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.service.order.api.IOrderService;
import com.mallcai.service.order.vo.PointOrderRefundAfterSaleRequest;
import com.mallcai.trade.global.service.pay.balance.BalanceOpService;
import com.mallcai.trade.common.mongo.vo.MongoOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author yl
 * @description 积分or余额执行器
 * @date 2019-07-21
 */
@Service
@Slf4j
public class ChangeExecuteService {

    @Autowired
    @Qualifier("caicaiTransactionTemplate")
    private TransactionTemplate caicaiTransactionTemplate;

    @Autowired
    @Qualifier("tradeTransactionTemplate")
    private TransactionTemplate tradeTransactionTemplate;
    @Resource
    private SettlementService settlementService;
    @Resource
    private IOrderService iOrderService;
    @Resource
    private PointRefundErrorLogDAO pointRefundErrorLogDAO;
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private BalanceOpService balanceOpService;
    @Resource
    private UserPickupChangeErrorLogDAO userPickupChangeErrorLogDAO;
    @Resource
    private OrderMongoDAO orderMongoDAO;
    @Resource
    private SettlementLogDAO settlementLogDAO;

    /**
     * 结算积分退还
     */
    public boolean handleReturnPoint(Integer point, OrderDO orderDO,
                                     DeliveredDetailDTO deliveredDetail) {
        SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();

        String orderId = orderDO.getOrderId();
        Integer preOrderPrice = orderDO.getTotalPointPrice();
        if (point.compareTo(preOrderPrice) > 0) {
            point = preOrderPrice;
        }
        boolean executeFlag =
                SettlementTypeEnum.CHANGE_UPDATE.getCode().equals(settlementLog.getType())
                        && SettlementStatusEnum.PROCESSING.getCode().equals(settlementLog.getStatus());
        if (!executeFlag) {
            if (!BooleanUtils.toBoolean(deliveredDetail.getOperateSettlement())
                    && !BooleanUtils.toBoolean(deliveredDetail.getIsTask())) {
                settlementService.insertWarnLog(deliveredDetail, SettlementTypeEnum.CHANGE_UPDATE.getCode());
            }
            return true;
        }

        Integer finalPoint = point;
        return tradeTransactionTemplate.execute(tradeStatus -> {
            settlementService
                    .updateSettlement(orderId, SettlementTypeEnum.SUCCESS_END.getCode(),
                            SettlementStatusEnum.SUCCESS.getCode(), deliveredDetail);
            try {
                PointOrderRefundAfterSaleRequest request = new PointOrderRefundAfterSaleRequest();
                request.setOrderId(orderId);
                request.setPoints(finalPoint);
                request.setRefundType(1);
                request.setRemark("门店取货");
                StandardResult result = iOrderService.pointOrderRefundAfterSale(request);
                if (!result.getSuccess()) {
                    log.info(">>>>>订单结算>>退积分失败>>orderId={},point={},msg={}", orderId, finalPoint,
                            result.getErrorMsg());
                    PointRefundErrorLogDO pointRefund = new PointRefundErrorLogDO();
                    pointRefund.setMsg(result.getErrorMsg());
                    pointRefund.setOrderId(orderId);
                    pointRefund.setPoint(finalPoint);
                    pointRefundErrorLogDAO.insertSelective(pointRefund);
                }
            } catch (Exception e) {
                log.error(">>>>>订单结算>>退积分失败>>orderId={},point={},msg={}", orderId, finalPoint,
                        e.toString());
                PointRefundErrorLogDO pointRefund = new PointRefundErrorLogDO();
                pointRefund.setMsg("退积分失败");
                pointRefund.setOrderId(orderId);
                pointRefund.setPoint(finalPoint);
                pointRefundErrorLogDAO.insertSelective(pointRefund);
            }
            return true;
        });

    }

    /**
     * 处理找零
     */
    public PlainResult handleChange(Integer change, DeliveredDetailDTO deliveredDetail) {
        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        // 找零总额不能超过预付总额(=微信或支付宝支付金额+余额支付金额)
        OrderDO orderDO = orderDAO.selectByOrderId(orderId);
        int balance = Objects.isNull(orderDO.getBalance()) ? 0 : orderDO.getBalance();
        Integer preOrderPrice = orderDO.getOrderPrice()
                .add(new BigDecimal(Integer.toString(balance))).intValue();
        if (change.compareTo(preOrderPrice) > 0) {
            change = preOrderPrice;
        }
        if (deliveredDetail.getForcedChangeSum().compareTo(change) > 0) {
            deliveredDetail.setForcedChangeSum(change);
        }
        boolean changeFlag = handleChange(deliveredDetail, change);
        if (!changeFlag) {
            log.warn("余额归还  操作失败");
            return PlainResultBuilder.fail(0, "余额归还  操作失败");
        }

        return PlainResultBuilder.success(null);
    }


    /**
     * db 数据处理
     */
    private boolean handleChange(DeliveredDetailDTO deliveredDetail, Integer change) {
        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();

        boolean executeFlag =
                SettlementTypeEnum.CHANGE_UPDATE.getCode().equals(settlementLog.getType())
                        && SettlementStatusEnum.PROCESSING.getCode().equals(settlementLog.getStatus());
        if (!executeFlag) {
            if (!BooleanUtils.toBoolean(deliveredDetail.getOperateSettlement())
                    && !BooleanUtils.toBoolean(deliveredDetail.getIsTask())) {
                settlementService.insertWarnLog(
                        deliveredDetail, SettlementTypeEnum.CHANGE_UPDATE.getCode());
            }
            return true;
        }
        boolean transFlag = tradeTransactionTemplate.execute(tradeStatus -> {
            SettlementLogDO settlementLogDO = new SettlementLogDO();
            settlementLogDO.setType(SettlementTypeEnum.SUCCESS_END.getCode());
            settlementLogDO.setStatus(SettlementStatusEnum.SUCCESS.getCode());
            settlementLogDO.setOrderId(orderId);
            int count = settlementLogDAO.updateBySettlement(settlementLogDO);
            if (count <= 0) {
                throw new BizException("更新settlement 失败");
            }
            boolean caicaiFlag = caicaiTransactionTemplate.execute(status -> {
                Date now = new Date();
                OrderDO orderUpdate = new OrderDO();
                orderUpdate.setChange(change);
                orderUpdate.setOrderId(orderId);
                orderUpdate.setUpdateTime(now);
                orderDAO.updateByOrderIdSelective(orderUpdate);
                MongoOrder set = new MongoOrder();
                set.setChange(change);
                set.setUpdateTime(DateFormatUtils.format(now, "yyyy-MM-dd"));
                MongoOrder where = new MongoOrder();
                where.setOrderId(orderId);
                orderMongoDAO.update(set, where, true);
                return true;
            });
            if (!caicaiFlag) {
                throw new BizException("余款额归还失败");
            }
            settlementLog.setType(SettlementTypeEnum.SUCCESS_END.getCode());
            settlementLog.setStatus(SettlementStatusEnum.SUCCESS.getCode());
            deliveredDetail.setSettlementLog(settlementLog);
            deliveredDetail.setOperateSettlement(true);
            return true;
        });
        if (transFlag) {
            handleUserBalance(change, deliveredDetail);
        }
        return transFlag;
    }

    /**
     * 处理余额
     */
    private void handleUserBalance(Integer totalChange, DeliveredDetailDTO deliveredDetail) {
        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        Integer operateId = deliveredDetail.getOperatorId();
        // 更新用户账户余额 给用户充值
        try {
            Map<String, Integer> totalChangeMap = Maps.newHashMap();
            totalChangeMap.put(orderId, totalChange);

            Map<String, Integer> forcedChangeMap = Maps.newHashMap();
            forcedChangeMap.put(orderId, deliveredDetail.getForcedChangeSum());
            log.info("找零开始>>>>>>>orderId:{}", orderId);
            com.mallcai.backend.common.api.PlainResult<Boolean> plainResult = iOrderService.holdChangeBeforeComment(totalChangeMap, forcedChangeMap, operateId);
            if (!plainResult.isSuccess()) {
                log.error(">>>>>订单结算>>余额找零失败>>totalChange={},msg={}", totalChange,
                        plainResult.getMessage());
                UserPickupChangeErrorLogDO chargeRefund = new UserPickupChangeErrorLogDO();
                chargeRefund.setUserId(deliveredDetail.getUserId());
                chargeRefund.setOrderIds(orderId);
                String msg = plainResult.getMessage();
                if(!StringUtils.isEmpty(msg) && msg.length() > 1900){
                    msg = msg.substring(0,1900);
                }
                chargeRefund.setMsg("调用交易中心返回结果：" + msg);
                chargeRefund.setTotalChange(totalChange);
                userPickupChangeErrorLogDAO.insertSelective(chargeRefund);
            }

        } catch (Exception e) {
            log.error(">>>>>订单结算>>余额找零异常>>totalChange=" + totalChange, e);
            UserPickupChangeErrorLogDO userPickupChangeErrorLogDO = new UserPickupChangeErrorLogDO();
            userPickupChangeErrorLogDO.setOrderIds(orderId);
            userPickupChangeErrorLogDO.setUserId(deliveredDetail.getUserId());
            userPickupChangeErrorLogDO.setMsg("余额找零异常");
            userPickupChangeErrorLogDO.setTotalChange(totalChange);
            userPickupChangeErrorLogDAO.insertSelective(userPickupChangeErrorLogDO);
        }
    }
}
