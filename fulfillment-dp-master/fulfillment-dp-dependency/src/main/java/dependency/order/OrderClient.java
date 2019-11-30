/*
package dependency.order;

import com.google.common.collect.Lists;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.youzan.api.common.response.PlainResult;
import com.youzan.tesla.core.annotation.TeslaCommand;
import com.youzan.tesla.core.annotation.TeslaInit;
import com.youzan.trade.component.utils.LogUtils;
import com.youzan.trade.core.service.operate.OrderOperateService;
import com.youzan.trade.core.service.operate.dto.OperateOrderRequestDTO;
import com.youzan.trade.core.service.query.OrderQueryService;
import com.youzan.trade.core.service.query.dto.OrderDTO;
import com.youzan.trade.core.service.query.dto.OrderQueryOption;
import com.youzan.trade.dc.common.exception.DeliveryBizException;
import com.youzan.trade.dc.common.exception.DeliveryDependencyException;
import com.youzan.trade.dc.common.exception.Errors;
import com.youzan.trade.dc.common.rpc.RpcOption;
import com.youzan.trade.dc.common.rpc.RpcProxy;
import com.youzan.trade.dc.common.utils.ResultBuildUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * trade-core订单操作接口
 *
 * @author hupp create date: 17/4/19
 *//*

@Slf4j
@Component
public class OrderClient {

  @Resource
  private OrderQueryService orderQueryService;

  @Resource
  private OrderOperateService orderOperateService;

  @Resource
  private TeslaOrderClient teslaOrderClient;

  private static final String CALL_TC_SOURCE = "trade-dc";

  private static final Integer ORDER_QUERY_LIMIT_ERROR = 101302002;

  */
/**
   * 获取订单信息
   *
   * @return OrderDTO
   *//*

  public OrderDTO queryByOrderNo(String orderNo) {
    PlainResult<OrderDTO> result = null;
    try {
      result = teslaOrderClient.queryByOrderNoWithFallback(orderNo);
    } catch (HystrixRuntimeException ex) {
      LogUtils.info(log, "[OrderClient.queryByOrderNo] fallback exceeds maximum - ex={}", ex);
      result = ResultBuildUtils.getCommonFailedResult(Errors.SystemBusyTryAgainLater);
    }

    if (result == null) {
      throw new DeliveryDependencyException(Errors.QueryOrderError);
    }

    if (!result.isSuccess()) {
      throw new DeliveryDependencyException(result.getCode(), result.getMessage());
    }

    if (result.getData() == null) {
      throw new DeliveryBizException(Errors.OrderNotExist);
    }
    return result.getData();
  }

  */
/**
   * 获取订单信息 (不打日志)
   *
   * @return OrderDTO
   *//*

  public OrderDTO queryByOrderNoWithNoLog(String orderNo) {
    OrderQueryOption orderQueryOption = new OrderQueryOption();
    orderQueryOption.setIncludeOrderItems(true);
    orderQueryOption.setIncludeReceiveInfo(true);
    orderQueryOption.setApp(CALL_TC_SOURCE);

    Map<String, Object> requestMap = new HashMap<>(2);
    requestMap.put("orderNo", orderNo);
    requestMap.put("orderQueryOption", orderQueryOption);

    RpcOption rpcOpt = new RpcOption();
    rpcOpt.setRequestDTO(requestMap);
    rpcOpt.setCallerClass(this.getClass().getSimpleName());
    rpcOpt.setCallerFunc("queryByOrderNoWithNoLog");
    rpcOpt.setAddLog(Boolean.FALSE);
    rpcOpt.setCallSegment(() -> orderQueryService.queryOrderByOrderNo(orderNo, orderQueryOption));

    PlainResult<OrderDTO> result = RpcProxy.call(rpcOpt);
    if (result == null) {
      throw new DeliveryDependencyException(Errors.QueryOrderError);
    }
    if (result.getData() == null) {
      throw new DeliveryBizException(Errors.OrderNotExist);
    }
    return result.getData();
  }

  */
/**
   * 批量查询订单
   *//*

  public List<OrderDTO> queryByOrderNos(List<String> orderNos) {
    return queryByOrderNos(orderNos, null);
  }
  */
/**
   * 批量查询订单
   *//*

  public List<OrderDTO> queryByOrderNos(List<String> orderNos, OrderQueryOption orderQueryOption) {
    Map<String, Object> requestMap = new HashMap<>(2);
    requestMap.put("orderNo", orderNos);
    if (orderQueryOption != null) {
      orderQueryOption.setApp(CALL_TC_SOURCE);
    }

    RpcOption rpcOpt = new RpcOption();
    rpcOpt.setRequestDTO(requestMap);
    rpcOpt.setCallerClass(this.getClass().getSimpleName());
    rpcOpt.setCallerFunc("queryByOrderNos");
    rpcOpt.setAddLog(Boolean.FALSE);
    rpcOpt.setCallSegment(() -> orderQueryService.batchQueryOrderByOrderNos(orderNos, orderQueryOption));

    PlainResult<Map<Long, OrderDTO>> result = RpcProxy.call(rpcOpt);
    if (result == null) {
      throw new DeliveryDependencyException(Errors.QueryOrderError);
    }
    if (result.getData() == null) {
      throw new DeliveryBizException(Errors.OrderNotExist);
    }

    List<OrderDTO> orderDTOs = Lists.newArrayList();
    result.getData().values().forEach(orderDTO -> orderDTOs.add(orderDTO));
    return orderDTOs;
  }

  */
/**
   * 根据订单ID查询订单
   * @param orderId
   * @param option
   * @return
   *//*

  public OrderDTO queryOrderById(Long orderId, OrderQueryOption option) {
    if (option != null) {
      option.setApp(CALL_TC_SOURCE);
    }
    Map<String, Object> requestMap = new HashMap<>(2);
    requestMap.put("orderId", orderId);
    requestMap.put("orderQueryOption", option);
    RpcOption rpcOpt = new RpcOption();
    rpcOpt.setRequestDTO(requestMap);
    rpcOpt.setCallerClass(this.getClass().getSimpleName());
    rpcOpt.setCallerFunc("queryOrderById");
    rpcOpt.setCallSegment(() -> orderQueryService.queryOrderById(orderId, option));
    PlainResult<OrderDTO> result = RpcProxy.call(rpcOpt);
    if (result == null) {
      throw new DeliveryDependencyException(Errors.QueryOrderError);
    }
    if (result.getData() == null) {
      throw new DeliveryBizException(Errors.OrderNotExist);
    }
    return result.getData();
  }

  */
/**
   * 佰显大哥明确返回失败重试也没有用
   * 订单变更为发货状态
   *
   * @return 是否成功
   *//*

  public boolean shipOrderById(Long orderId) {
    PlainResult<Boolean> result = null;
    try {
      result = teslaOrderClient.shipOrderById(orderId);
    } catch (HystrixRuntimeException ex) {
      LogUtils.info(log, "[OrderClient.shipOrderById] fallback exceeds maximum - ex={}", ex);
      result = ResultBuildUtils.getCommonFailedResult(Errors.SystemBusyTryAgainLater);
    }

    if (result == null) {
      return false;
    }

    return result.getData() == null ? false : result.getData();
  }

  */
/**
   * 佰显大哥明确返回失败重试也没有用
   * 订单变更为发货状态
   *
   * @return 是否成功
   *//*

  public boolean waitSellerShipOrder(Long orderId) {
    OperateOrderRequestDTO operateOrderRequestDTO = new OperateOrderRequestDTO();
    operateOrderRequestDTO.setOrderId(orderId);
    operateOrderRequestDTO.setApp(CALL_TC_SOURCE);

    RpcOption rpcOpt = new RpcOption();
    rpcOpt.setRequestDTO(operateOrderRequestDTO);
    rpcOpt.setCallerClass(this.getClass().getSimpleName());
    rpcOpt.setCallerFunc("waitSellerShipOrder");
    rpcOpt.setCallSegment(() -> orderOperateService.waitSellerShipOrder(operateOrderRequestDTO));

    PlainResult<Boolean> result = RpcProxy.call(rpcOpt);
    if (result == null) {
      return false;
    }
    return result.getData() == null ? false : result.getData();
  }

  */
/**
   * 佰显大哥明确返回失败重试也没有用
   * 订单确认收货
   *
   * @return 是否成功
   *//*

  public Boolean confirmReceiveOrder(Long orderId) {
    OperateOrderRequestDTO operateOrderRequestDTO = new OperateOrderRequestDTO();
    operateOrderRequestDTO.setOrderId(orderId);
    operateOrderRequestDTO.setApp(CALL_TC_SOURCE);

    RpcOption rpcOpt = new RpcOption();
    rpcOpt.setRequestDTO(operateOrderRequestDTO);
    rpcOpt.setCallerClass(this.getClass().getSimpleName());
    rpcOpt.setCallerFunc("confirmReceiveOrder");
    rpcOpt.setIgnoreRpcResult(false);
    rpcOpt.setCallSegment(
        () -> orderOperateService.confirmReceiveOrderByUser(operateOrderRequestDTO));

    PlainResult<Boolean> result = RpcProxy.call(rpcOpt);
    if (result == null) {
      return null;
    }
    return result.getData() == null ? false : result.getData();
  }

  */
/**
   * 查询订单(不抛异常)
   *
   * @return OrderDTO
   *//*

  public OrderDTO queryByOrderNoWithNoException(String orderNo) {
    OrderDTO orderDTO;
    try {
      orderDTO = queryByOrderNo(orderNo);
      if (orderDTO == null) {
        LogUtils.info(log, "[OrderClient.queryByOrderNoWithNoException] get order fail. message={}",
                      orderNo);
        return null;
      }
    } catch (Exception e) {
      LogUtils.info(log, "[OrderClient.queryByOrderNoWithNoException] get order fail. message={}",
                    orderNo);
      return null;
    }
    return orderDTO;
  }

  public OrderDTO queryByOrderNoWithNotExist(String orderNo){
    OrderQueryOption orderQueryOption = new OrderQueryOption();
    orderQueryOption.setIncludeOrderItems(true);
    orderQueryOption.setIncludeItemRealPay(true);
    orderQueryOption.setIncludeReceiveInfo(true);
    orderQueryOption.setIncludeExt(true);
    orderQueryOption.setApp(CALL_TC_SOURCE);

    Map<String, Object> requestMap = new HashMap<>(2);
    requestMap.put("orderNo", orderNo);
    requestMap.put("orderQueryOption", orderQueryOption);

    RpcOption rpcOpt = new RpcOption();
    rpcOpt.setRequestDTO(requestMap);
    rpcOpt.setCallerClass(this.getClass().getSimpleName());
    rpcOpt.setCallerFunc("queryByOrderNo");
    rpcOpt.setCallSegment(() -> orderQueryService.queryOrderByOrderNo(orderNo, orderQueryOption));

    PlainResult<OrderDTO> result = RpcProxy.call(rpcOpt);
    if (result == null) {
      throw new DeliveryDependencyException(Errors.QueryOrderError);
    }

    if (!result.isSuccess() && result.getCode() == ORDER_QUERY_LIMIT_ERROR) {
      throw new DeliveryDependencyException(Errors.SystemBusyTryAgainLater);
    }

    return result.getData();
  }

  @Component
  @TeslaInit
  static class TeslaOrderClient {

    @Resource
    private OrderQueryService orderQueryService;

    @Resource
    private OrderOperateService orderOperateService;

    @TeslaCommand(
      groupKey = "OrderClient",
      commandKey = "queryByOrderNo",
      fallbackMethod = "queryByOrderNoFallback"
    )
    public PlainResult<OrderDTO> queryByOrderNoWithFallback(String orderNo) {
      OrderQueryOption orderQueryOption = new OrderQueryOption();
      orderQueryOption.setIncludeOrderItems(true);
      orderQueryOption.setIncludeItemRealPay(true);
      orderQueryOption.setIncludeReceiveInfo(true);
      orderQueryOption.setIncludeExt(true);
      orderQueryOption.setApp(CALL_TC_SOURCE);

      Map<String, Object> requestMap = new HashMap<>(2);
      requestMap.put("orderNo", orderNo);
      requestMap.put("orderQueryOption", orderQueryOption);

      RpcOption rpcOpt = new RpcOption();
      rpcOpt.setRequestDTO(requestMap);
      rpcOpt.setCallerClass(this.getClass().getSimpleName());
      rpcOpt.setCallerFunc("queryByOrderNo");
      rpcOpt.setIgnoreRpcResult(false);
      rpcOpt.setCallSegment(() -> orderQueryService.queryOrderByOrderNo(orderNo, orderQueryOption));

      PlainResult<OrderDTO> result = RpcProxy.call(rpcOpt);
      return result;
    }

    // 根据订单号查询订单降级 (直接快速失败)
    public PlainResult<OrderDTO> queryByOrderNoFallback(String orderNo) {
      PlainResult<OrderDTO> result = ResultBuildUtils.getCommonFailedResult(Errors.SystemBusyTryAgainLater);
      return result;
    }

    @TeslaCommand(
      groupKey = "OrderClient",
      commandKey = "shipOrderById",
      fallbackMethod = "shipOrderByIdFallback"
    )
    public PlainResult<Boolean> shipOrderById(Long orderId) {
      OperateOrderRequestDTO operateOrderRequestDTO = new OperateOrderRequestDTO();
      operateOrderRequestDTO.setOrderId(orderId);
      operateOrderRequestDTO.setApp(CALL_TC_SOURCE);

      RpcOption rpcOpt = new RpcOption();
      rpcOpt.setRequestDTO(operateOrderRequestDTO);
      rpcOpt.setCallerClass(this.getClass().getSimpleName());
      rpcOpt.setCallerFunc("shipOrderById");
      rpcOpt.setCallSegment(() -> orderOperateService.shipOrderById(operateOrderRequestDTO));

      PlainResult<Boolean> result = RpcProxy.call(rpcOpt);
      return result;
    }

    // 更新订单状态为已发货 (返回失败, 触发补偿)
    public PlainResult<Boolean> shipOrderByIdFallback(Long orderId) {
      return ResultBuildUtils.getCommonFailedResult(Errors.SystemBusyTryAgainLater);
    }

  }

}
*/
