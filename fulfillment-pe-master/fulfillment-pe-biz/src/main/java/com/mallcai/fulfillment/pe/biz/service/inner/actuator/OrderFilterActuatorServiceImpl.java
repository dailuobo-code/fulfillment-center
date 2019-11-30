package com.mallcai.fulfillment.pe.biz.service.inner.actuator;

import com.mallcai.fulfillment.pe.biz.service.inner.OrderFilterActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderFilterRuleService;
import com.mallcai.fulfillment.pe.common.utils.ObjectCopyUtil;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-29 01:13:59
 */
@Service
public class OrderFilterActuatorServiceImpl implements OrderFilterActuatorService {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderDO> filter(List<OrderDO> orders) {

        // deep copy
        List<OrderDO> needFilterOrders = orders.stream().map(order -> (OrderDO) ObjectCopyUtil.deeplyCopy(order)).collect(Collectors.toList());

        Map<String, OrderFilterRuleService> rules = listableBeanFactory.getBeansOfType(OrderFilterRuleService.class);

        for (OrderFilterRuleService rule : rules.values()) {

            needFilterOrders = rule.executeRule(needFilterOrders);
        }

        List<Long> oriIds = orders.stream().map(OrderDO::getId).collect(Collectors.toList());
        List<Long> finalIds = needFilterOrders.stream().map(OrderDO::getId).collect(Collectors.toList());

        oriIds.removeAll(finalIds);

        if (!oriIds.isEmpty()) {
            orderDao.updateStatusByIdUnderStatus(oriIds, OrderStatusEnum.FILTERED.getStatus(), OrderStatusEnum.INIT.getStatus());
        }

        return needFilterOrders;
    }
}
