package com.mallcai.fulfillment.dp.biz.service.impl.inner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.DateTimeUtils;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.request.*;
import com.mallcai.fulfillment.dp.biz.service.impl.Repository.DistOrderRepository;
import com.mallcai.fulfillment.dp.biz.service.impl.Repository.IDBuilderUtils;
import com.mallcai.fulfillment.dp.biz.service.impl.enums.ConfirmOrderStatusEnum;
import com.mallcai.fulfillment.dp.biz.service.impl.enums.DistOrderStatusEnum;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dao.DistOrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject.DistOrderDO;
import com.mallcai.service.order.api.IOrderService;
import com.mallcai.service.order.req.HomeOrderDeliverRequest;
import com.mallcai.service.order.req.HomeOrderReceiveRequest;
import com.mallcai.service.order.req.OrderQueryOptions;
import com.mallcai.service.order.req.OrderQueryParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 配送单执行
 *
 * @author bh.zhong
 * @date 2019/9/8 8:00 PM
 */
@Service
@Slf4j
public class DeliveryOrderInnerService {

    @Resource
    private DistOrderDAO distOrderDAO;

    @Resource
    private DistOrderRepository distOrderRepository;

    @Resource
    private IOrderService iOrderService;


    public PlainResult<Boolean> orderSend(DeliveryOrderDTO deliveryOrderDTO) {
        //step1:校验当前订单是否允许发货  暂时不做

        //step2:查询当前配送单是否已经存在
        DistOrderDO distOrderDO;
        List<DistOrderDO> distOrderDOS = distOrderDAO.selectByOrderNo(deliveryOrderDTO.getOrderNo());
        if (CollectionUtils.isNotEmpty(distOrderDOS) && distOrderDOS.size() > 1) {
            return PlainResultBuilder.fail(0, "单运单发货异常，存在多运单物流信息");
        }
        distOrderDO = null;
        if (CollectionUtils.isNotEmpty(distOrderDOS)) {
            distOrderDO = distOrderDOS.get(0);
        }
        if (distOrderDO == null) {
            Date deliveryTime=new Date();
            distOrderRepository.saveDistOrder(deliveryOrderDTO,deliveryTime);
            HomeOrderDeliverRequest homeOrderDeliverRequest = new HomeOrderDeliverRequest();
            homeOrderDeliverRequest.setOrderId(deliveryOrderDTO.getOrderNo());
            homeOrderDeliverRequest.setLogisticNo(deliveryOrderDTO.getDeliveryInfoDTO().getExpressMerchantDTO().getExpressNo());
            homeOrderDeliverRequest.setLogisticCompany(deliveryOrderDTO.getDeliveryInfoDTO().getExpressMerchantDTO().getExpressName());
            homeOrderDeliverRequest.setOperateTime(deliveryTime);
            //调用交易发货
            PlainResult<Boolean> result = iOrderService.homeOrderDeliver(homeOrderDeliverRequest);
            //修改配送单状态
            if (result.isSuccess() && result.getData()) {
                distOrderRepository.updateDistOrder(DistOrderStatusEnum.DELIVERED.getCode(), DistOrderStatusEnum.NON_DELIVERY.getCode(), deliveryOrderDTO.getOrderNo());
            } else {
                log.error("调用交易发货失败,iOrderService result{}",result);
                //补偿
            }
        } else if (DistOrderStatusEnum.needDeliver(distOrderDO.getStatus())) {
            HomeOrderDeliverRequest homeOrderDeliverRequest = new HomeOrderDeliverRequest();
            homeOrderDeliverRequest.setOrderId(distOrderDO.getOrderNo());
            homeOrderDeliverRequest.setLogisticCompany(deliveryOrderDTO.getDeliveryInfoDTO().getExpressMerchantDTO().getExpressName());
            homeOrderDeliverRequest.setOperateTime(distOrderDO.getCreateTime());
            homeOrderDeliverRequest.setLogisticNo(distOrderDO.getExpressNo());
            //调用交易发货
            PlainResult<Boolean> result = iOrderService.homeOrderDeliver(homeOrderDeliverRequest);
            //修改配送单状态
            if (result.isSuccess() && result.getData()) {
                distOrderRepository.updateDistOrder(DistOrderStatusEnum.DELIVERED.getCode(), DistOrderStatusEnum.NON_DELIVERY.getCode(), deliveryOrderDTO.getOrderNo());
            } else {
                log.error("调用交易发货失败,iOrderService result{}",result);
                //补偿
            }
        }
        return PlainResultBuilder.success(true);
    }


    /**
     * 批量确认收货
     *
     * @param confirmTime 确认时间
     * @return
     */
    public PlainResult<Boolean> batchConfirmOrder(Date confirmTime) {
        //step2:查询当前配送单是否已经存在
        List<DistOrderDO> distOrderDOS = distOrderDAO.searchByCreateTime(DateTimeUtils.dateToStr(confirmTime));
        if (CollectionUtils.isEmpty(distOrderDOS)) {
            return PlainResultBuilder.success(true);
        } else {
            distOrderDOS.forEach(distOrderDO -> {
                HomeOrderReceiveRequest homeOrderReceiveRequest = new HomeOrderReceiveRequest();
                homeOrderReceiveRequest.setOrderId(distOrderDO.getOrderNo());
                homeOrderReceiveRequest.setUserId(distOrderDO.getUserId());
                homeOrderReceiveRequest.setOperateTime(new Date());
                PlainResult<Boolean> result = iOrderService.homeOrderReceive(homeOrderReceiveRequest);
                if (result.isSuccess() && result.getData()) {
                    distOrderDAO.updateConfirmStatus(ConfirmOrderStatusEnum.CONFIRM_ORDER.getCode(), distOrderDO.getOrderNo());
                } else {
                    log.error("订单交易确认调用出错,distOrderDO:{},result:{}", distOrderDO, JSON.toJSONString(result));
                }
            });
        }
        return PlainResultBuilder.success(true);
    }

    public PlainResult<Boolean> orderMultiPkgSend(DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO) {
        List<DistOrderDO> distOrderDOS = distOrderDAO.selectByOrderNo(deliveryOrderMultiPkgDTO.getOrderNo());
        OrderQueryOptions orderQueryOptions = new OrderQueryOptions();
        orderQueryOptions.setIncludeDelOrder(false);
        if (iOrderService.queryOrder(new OrderQueryParam(deliveryOrderMultiPkgDTO.getOrderNo(),
                orderQueryOptions)) == null) {
            log.warn("订单不存在，orderNo:{}", deliveryOrderMultiPkgDTO.getOrderNo());
            return PlainResultBuilder.fail(Errors.ORDERID_NOT_EXIST_EXCEPTION.getCode(), "订单" + deliveryOrderMultiPkgDTO.getOrderNo() + "不存在");
        }
        if (CollectionUtils.isEmpty(distOrderDOS)) {
            Date deliveryTime = new Date();
            //保存配送单信息
            distOrderRepository.saveDistOrders(deliveryOrderMultiPkgDTO, deliveryTime);
            //调用交易发货，并修改库中状态为以发货
            notifyMultiPkgSend(deliveryOrderMultiPkgDTO.getOrderNo(), deliveryTime);
        } else if (DistOrderStatusEnum.needDeliver(distOrderDOS.get(0).getStatus())) {
            //调用交易发货，并修改库中状态为以发货
            int count = distOrderRepository.updateDistOrderMultiPkgInfo(deliveryOrderMultiPkgDTO, distOrderDOS.get(0));
            if (count <= 0) {
                log.error("发货失败,deliveryOrderMultiPkgDTO:{}, update count:{}", JSONObject.toJSONString(deliveryOrderMultiPkgDTO), count);
                return PlainResultBuilder.fail(0, "插入发货信息失败");
            }
            if (count != distOrderDOS.size() + deliveryOrderMultiPkgDTO.getDistOrderInfoList().size()) {
                log.error("发货数据异常,deliveryOrderMultiPkgDTO:{}, update count:{}", JSONObject.toJSONString(deliveryOrderMultiPkgDTO), count);
                return PlainResultBuilder.fail(0, "插入发货信息异常，部分运单入库失败");
            }
            String orderNo = distOrderDOS.get(0).getOrderNo();
            Date createTime = distOrderDOS.get(0).getCreateTime();
            notifyMultiPkgSend(orderNo, createTime);

        }
        return PlainResultBuilder.success(true);
    }

    private void notifyMultiPkgSend(String orderNo, Date deliveryTime) {
        HomeOrderDeliverRequest homeOrderDeliverRequest = new HomeOrderDeliverRequest();
        homeOrderDeliverRequest.setOrderId(orderNo);
        homeOrderDeliverRequest.setOperateTime(deliveryTime);
        //调用交易发货
        PlainResult<Boolean> result = iOrderService.homeOrderDeliver(homeOrderDeliverRequest);
        //修改配送单状态
        if (result.isSuccess() && result.getData()) {
            distOrderRepository.updateDistOrder(DistOrderStatusEnum.DELIVERED.getCode(), DistOrderStatusEnum.NON_DELIVERY.getCode(), orderNo);
        } else {
            log.error("调用交易发货失败,iOrderService result{}",result);
            //补偿
        }
    }
}
