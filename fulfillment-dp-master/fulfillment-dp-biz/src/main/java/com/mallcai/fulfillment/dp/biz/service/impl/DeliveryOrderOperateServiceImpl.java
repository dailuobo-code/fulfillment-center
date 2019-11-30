package com.mallcai.fulfillment.dp.biz.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.constants.DeliveryOrderConstants;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.DeliveryOrderOperateService;
import com.mallcai.fulfillment.dp.api.request.ModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.request.MultiPkgModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.request.OrderConfirmReceiveDTO;
import com.mallcai.fulfillment.dp.biz.service.impl.Repository.DistOrderRepository;
import com.mallcai.fulfillment.dp.biz.service.impl.enums.ConfirmOrderStatusEnum;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.DeliveryOrderOperateServiceParamChecker;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.MultiPkgServiceChecker;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dao.DistOrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject.DistOrderDO;
import com.mallcai.service.order.api.IOrderOperateService;
import com.mallcai.service.order.api.IOrderService;
import com.mallcai.service.order.req.HomeOrderReceiveRequest;
import com.mallcai.service.order.req.LogisticUpdateRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * @author: Liu Yang
 * @description 确认收货实现I
 * @date: 2019-09-06 15:53
 */
@Service
@Slf4j
public class DeliveryOrderOperateServiceImpl implements DeliveryOrderOperateService {

  @Resource
  private IOrderService iOrderService;
  @Resource
  private IOrderOperateService iOrderOperateService;
  @Resource
  private DeliveryOrderOperateServiceParamChecker deliveryOrderOperateServiceParamChecker;
  @Resource
  private DistOrderDAO distOrderDAO;
  @Resource
  private DistOrderRepository distOrderRepository;

  @Override
  public PlainResult<Boolean> orderConfirmReceive(OrderConfirmReceiveDTO orderConfirmReceiveDTO) {
    PlainResult plainResult = deliveryOrderOperateServiceParamChecker.checkOrderConfirmQuery(orderConfirmReceiveDTO);
    if(!plainResult.isSuccess()){
      return plainResult;
    }
    //调用下游订单中心确认收货接口
    HomeOrderReceiveRequest homeOrderReceiveRequest = new HomeOrderReceiveRequest();
    homeOrderReceiveRequest.setOrderId(orderConfirmReceiveDTO.getOrderNo());
    homeOrderReceiveRequest.setUserId(Integer.valueOf(orderConfirmReceiveDTO.getUserId()));
    homeOrderReceiveRequest.setOperateTime(new Date());
    List<DistOrderDO> distOrderDOS = distOrderDAO.selectByOrderNo(orderConfirmReceiveDTO.getOrderNo());
    if (CollectionUtils.isEmpty(distOrderDOS)) {
      log.error("收货订单不存在,orderConfirmReceiveDTO:{}", JSON.toJSONString(orderConfirmReceiveDTO));
      return PlainResultBuilder.fail(0, "订单确认调用出错");
    }
    int[] noneConfirmCount = new int[1];
    if (allConfirmed(distOrderDOS, noneConfirmCount)) {
      return PlainResultBuilder.success(null);
    }
    PlainResult<Boolean> result = iOrderService.homeOrderReceive(homeOrderReceiveRequest);
    if(result.isSuccess() && result.getData()){
      int count = distOrderDAO.updateConfirmStatus(ConfirmOrderStatusEnum.CONFIRM_ORDER.getCode(),
              orderConfirmReceiveDTO.getOrderNo());
      if(count <= 0){
        log.error("更新订单为已收货失败,orderConfirmReceiveDTO:{}, update count:", JSONObject.toJSONString(orderConfirmReceiveDTO), count);
        return PlainResultBuilder.fail(0, "更新订单为已收货失败");
      }
      if ( count != noneConfirmCount[0]) {
        log.error("更新订单为已收货状态异常，部分更新失败,orderConfirmReceiveDTO:{}, update count:", JSONObject.toJSONString(orderConfirmReceiveDTO), count);
        return PlainResultBuilder.fail(0, "更新订单为已收货状态异常，部分运单更新失败");
      }
    } else {
      log.error("订单确认调用出错,orderConfirmReceiveDTO{}", JSON.toJSONString(orderConfirmReceiveDTO));
      return PlainResultBuilder.fail(0, "订单确认调用出错");
    }
    return PlainResultBuilder.success(true);
  }

  private boolean allConfirmed(List<DistOrderDO> distOrderDOS, int[] noneConfirmCount) {
    List<DistOrderDO> noConfirmedList = distOrderDOS.stream().filter(distOrderDO -> !ConfirmOrderStatusEnum.isConfirm(distOrderDO.getConfirmStatus()))
            .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(noConfirmedList)) {
      return true;
    }

    noneConfirmCount[0] = noConfirmedList.size();
    return false;
  }

  @Override
  public PlainResult<Boolean> modifyExpress(ModifyExpressDTO modifyExpressDTO) {
    //校验参数
    deliveryOrderOperateServiceParamChecker.checkEditExpressInfoQuery(modifyExpressDTO);
    //查询订单是否存在
    List<DistOrderDO> distOrderDOS = distOrderDAO.selectByOrderNo(modifyExpressDTO.getOrderNo());
    if (CollectionUtils.isNotEmpty(distOrderDOS) && distOrderDOS.size() > 1) {
      return PlainResultBuilder.fail(0, "单运单修改物流信息，存在多运单物流信息");
    }
    DistOrderDO distOrderDO = null;
    if (CollectionUtils.isNotEmpty(distOrderDOS)) {
      distOrderDO = distOrderDOS.get(0);
    }
    if (distOrderDO == null) {
      log.error("收货订单不存在,modifyExpressDTO:{}", JSONObject.toJSONString(modifyExpressDTO));
      return PlainResultBuilder.fail(0, "物流信息查询出错，订单不存在");
    }
    //已经名字相等了
    if(StringUtils.equals(distOrderDO.getExpressName(),modifyExpressDTO.getExpressName())&&StringUtils.equals(distOrderDO.getExpressNo(),modifyExpressDTO.getExpressNo())){
      return PlainResultBuilder.success(true);
    }
    //只能修改十次
    if(distOrderDO.getModifyTimes()== DeliveryOrderConstants.MAX_MODIFY_LIMIT){
      log.error("该订单快递信息修改次数已达10次,modifyExpressDTO:{}", JSONObject.toJSONString(modifyExpressDTO));
      return PlainResultBuilder.fail(0, "该订单快递信息修改次数已达10次");
    }
    //此处调用订单中心修改承运公司接口
    LogisticUpdateRequest logisticUpdateRequest = new LogisticUpdateRequest();
    logisticUpdateRequest.setOrderId(distOrderDO.getOrderNo());
    logisticUpdateRequest.setLogisticNo(modifyExpressDTO.getExpressNo());
    logisticUpdateRequest.setLogisticCompany(modifyExpressDTO.getExpressName());
    PlainResult<Boolean> result = iOrderOperateService.updateHomeOrderLogistic(logisticUpdateRequest);
    if (result.isSuccess() && result.getData()) {
      //履约自己库承运公司修改
      int count=distOrderDAO.updateExpressInfo(modifyExpressDTO.getExpressName(),modifyExpressDTO.getExpressNo(),modifyExpressDTO.getOrderNo());
      if(count<=0){
        log.error("更新快递信息失败,modifyExpressDTO:{}", JSONObject.toJSONString(modifyExpressDTO));
        return PlainResultBuilder.fail(0, "更新快递信息失败");
      }
    } else {
      log.error("订单中心修改快递公司服务调用出错,logisticUpdateRequest:{}", JSONObject.toJSONString(logisticUpdateRequest));
      return PlainResultBuilder.fail(0, "订单中心修改快递公司服务调用出错");
    }
    return PlainResultBuilder.success(true);
  }

    @Override
    public PlainResult<Boolean> modifyMultiPkgExpress(MultiPkgModifyExpressDTO multiPkgModifyExpressDTO) {
        //校验参数
        PlainResult plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }

        //查询订单是否存在
        List<DistOrderDO> distOrderDOS = distOrderDAO.selectByOrderNo(multiPkgModifyExpressDTO.getOrderNo());
        if (CollectionUtils.isEmpty(distOrderDOS)) {
            log.error("订单发货信息不存在,multiPkgModifyExpressDTO:{}", JSONObject.toJSONString(multiPkgModifyExpressDTO));
            return PlainResultBuilder.fail(0, "物流信息查询出错，订单发货信息不存在");
        }

        //只能修改十次
        if(distOrderDOS.get(0).getModifyTimes() == DeliveryOrderConstants.MAX_MODIFY_LIMIT){
            log.error("该订单快递信息修改次数已达10次, multiPkgModifyExpressDTO:{}", JSONObject.toJSONString(multiPkgModifyExpressDTO));
            return PlainResultBuilder.fail(0, "该订单快递信息修改次数已达10次");
        }

        //无需通知交易快递信息修改
        //全量替换，将原有订单配送单信息置为删除，然后添加新的配送单信息
        int count = distOrderRepository.updateDistOrderMultiPkgInfo(multiPkgModifyExpressDTO, distOrderDOS.get(0));
        if (count <= 0) {
            log.error("更新快递信息失败,multiPkgModifyExpressDTO:{}, update count:{}", JSONObject.toJSONString(multiPkgModifyExpressDTO), count);
            return PlainResultBuilder.fail(0, "更新物流信息失败");
        }
        if (count != distOrderDOS.size() + multiPkgModifyExpressDTO.getDistOrderInfoList().size()) {
            log.error("更新快递信息异常,multiPkgModifyExpressDTO:{}, update count:{}", JSONObject.toJSONString(multiPkgModifyExpressDTO), count);
            return PlainResultBuilder.fail(0, "更新快递信息异常，部分运单信息更新失败");
        }
        return PlainResultBuilder.success(true);
    }
}
