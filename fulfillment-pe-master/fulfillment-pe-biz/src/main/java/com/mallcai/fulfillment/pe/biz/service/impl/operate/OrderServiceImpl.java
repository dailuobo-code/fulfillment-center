package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.OrderService;
import com.mallcai.fulfillment.pe.biz.service.inner.RedisService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.promise.util.DistriCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-27 23:01:19
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RedisService redisService;

    @Override
    public PlainResult<?> cancelOrder(String transOrderId) {

        List<OrderDO> orders = orderDao.selectByTransOrderId(transOrderId);

        if (orders.size() == 0){

            log.error("订单不存在,transOrderId:{}", transOrderId);
            throw new BizException(Errors.ORDER_NOT_EXIST_ERROR);
        }

        List<Long> orderIds = new ArrayList<>();

        for (OrderDO orderDO : orders){

            if (!OrderStatusEnum.INIT.getStatus().equals(orderDO.getStatus())){

                if (OrderStatusEnum.CANCEL.getStatus().equals(orderDO.getStatus())){

                    log.info("订单已取消,orderId:{}", orderDO.getId());
                    throw new BizException(Errors.ORDER_CANCELED);
                }
                log.error("当前状态不可取消,orderId,:{}, status:{}", orderDO.getId(), orderDO.getStatus());
                throw new BizException(Errors.ORDER_CAN_NOT_CANCEL);
            }

            orderIds.add(orderDO.getId());
        }

        try {
            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.CANCEL.getStatus(), OrderStatusEnum.INIT.getStatus());
            redisCountDesc(orders);
        } catch (Exception e) {
            log.error("订单取消异常，ids:{}", StringUtils.join(orderIds, Constants.SYMBOL_COMMA));
            throw new BizException(Errors.FULFILLMENT_SYSTEM_ERROR);
        }

        return PlainResult.ok();
    }

    private void redisCountDesc(List<OrderDO> orders) {
        for (OrderDO order : orders) {
            GroupValueEnum groupValueEnum = GroupValueEnum.fromValue(order.getGroupValue());
            if (GroupValueEnum.ORDER_TYPE_STANDARD.equals(groupValueEnum)) {
                redisService.decrBy(DistriCacheUtil.getCapacityKey(order.getWarehouseId(),groupValueEnum.getCategoryType(),groupValueEnum.getAttributeType(),new Date()), 1);
                break;
            }
        }
    }

}
