package com.mallcai.fulfillment.dp.biz.service.impl.Repository;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.common.constants.Constants;
import com.mallcai.fulfillment.common.exception.BizException;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.dp.api.request.*;
import com.mallcai.fulfillment.dp.biz.service.impl.enums.ConfirmOrderStatusEnum;
import com.mallcai.fulfillment.dp.biz.service.impl.enums.DeletedStatusEnum;
import com.mallcai.fulfillment.dp.biz.service.impl.enums.DistOrderStatusEnum;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dao.DistOrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dao.DistOrderItemDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject.DistOrderDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject.DistOrderItemDO;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 配送单聚合仓储
 *
 * @author bh.zhong
 * @date 2019/9/8 8:00 PM
 */
@Slf4j
@Service
public class DistOrderRepository {

    @Resource
    private DistOrderDAO distOrderDAO;

    @Resource
    private DistOrderItemDAO distOrderItemDAO;

    @Autowired
    @Qualifier("fdpTransactionTemplate")
    private TransactionTemplate fdpTransactionTemplate;

//    public void batchSaveDistOrder(List<DeliveryOrderDTO> deliveryOrderDTOs) {
//        List<DistOrderDO> distOrderDOS = new ArrayList<>();
//        List<DistOrderItemDO> distOrderItemDOS = new ArrayList<>();
//        deliveryOrderDTOs.forEach(deliveryOrderDTO -> {
//            DistOrderDO distOrderDO = toDistOrder(deliveryOrderDTO);
//            distOrderDOS.add(distOrderDO);
//            distOrderItemDOS.addAll(toDistOrderItem(deliveryOrderDTO, distOrderDO.getDistOrderNo()));
//        });
//        // 根据数据库返回的影响的行数, 判断是否成功
//        fdpTransactionTemplate.execute(status -> {
//            try {
//                distOrderDAO.batchInsertDistOrder(distOrderDOS);
//                distOrderItemDAO.batchInsertDistOrderItem(distOrderItemDOS);
//            } catch (Exception e) {
//                log.error("DistOrderRepository.batchSaveDistOrder 数据库插入数据是不,deliveryOrderDTOs:{}", JSON.toJSONString(deliveryOrderDTOs), e);
//                throw new BizException(Errors.DB_EXECUTE_EXCEPTION);
//            }
//            return true;
//        });
//    }

    public int updateDistOrder(Integer status, Integer preStatus, String orderNo) {
        return distOrderDAO.updateDistOrderStatus(status, preStatus, orderNo);
    }


    /**
     * 领域层实现，简单在这里转换
     *
     * @return
     */
    private DistOrderDO toDistOrder(String orderNo, Integer cityId, Long storeId,
                                    DeliveryInfoDTO deliveryInfoDTO, Date deliveryTime) {
        //收货人地址信息
        DeliveryAddressDTO receiverAddress = deliveryInfoDTO.getReceiverAddress();
        //收货人详细信息
        DeliveryUserDTO receiverUser = deliveryInfoDTO.getReceiverUser();
        DistOrderDO distOrderDO = new DistOrderDO();
        distOrderDO.setDistOrderNo(IDBuilderUtils.build());
        distOrderDO.setCityId(cityId);
        distOrderDO.setDistType(deliveryInfoDTO.getDeliveryType());
        distOrderDO.setExpressNo(deliveryInfoDTO.getExpressMerchantDTO().getExpressNo());
        distOrderDO.setStoreId(storeId);
        distOrderDO.setStatus(DistOrderStatusEnum.NON_DELIVERY.getCode());
        distOrderDO.setOrderNo(orderNo);
        distOrderDO.setReceiverProvince(receiverAddress.getProvince());
        distOrderDO.setReceiverCity(receiverAddress.getCity());
        distOrderDO.setReceiverCounty(receiverAddress.getCounty());
        distOrderDO.setReceiverAddress(receiverAddress.getAddressDetail());
        distOrderDO.setReceiverName(receiverUser.getName());
        distOrderDO.setReceiverPhone(receiverUser.getPhone());
        distOrderDO.setReceiverUserId(receiverUser.getUserId());
        distOrderDO.setConfirmStatus(ConfirmOrderStatusEnum.NO_CONFIRM_ORDER.getCode());
        distOrderDO.setUserId(receiverUser.getUserId());
        distOrderDO.setExpressName(deliveryInfoDTO.getExpressMerchantDTO().getExpressName());
        distOrderDO.setModifyTimes(0);
        distOrderDO.setIsDeleted(DeletedStatusEnum.NON_DELETED.getCode());
        distOrderDO.setCreateTime(deliveryTime);
        return distOrderDO;
    }
    /**
     * 保存配送单
     */
    public int saveDistOrder(DeliveryOrderDTO deliveryOrderDTO,Date deliveryTime) {
        DistOrderDO distOrderDO = toDistOrder(deliveryOrderDTO.getOrderNo(), deliveryOrderDTO.getCityId(),
                deliveryOrderDTO.getStoreId(), deliveryOrderDTO.getDeliveryInfoDTO(), deliveryTime);
        List<DistOrderItemDO> distOrderItemDOS = toDistOrderItem(deliveryOrderDTO, distOrderDO.getDistOrderNo());
        return fdpTransactionTemplate.execute(status -> {
            try {
                int affectRow = distOrderDAO.insertDistOrder(distOrderDO);
                distOrderItemDAO.batchInsertDistOrderItem(distOrderItemDOS);
                return affectRow;
            } catch (Exception e) {
                log.error("DistOrderRepository.batchSaveDistOrder 数据库插入数据是不,deliveryOrderDTOs:{}", JSON.toJSONString(deliveryOrderDTO), e);
                throw new BizException(Errors.DB_EXECUTE_EXCEPTION);
            }
        });
    }



    /**
     * 领域层实现，简单在这里转换
     *
     * @return
     */
    private List<DistOrderItemDO> toDistOrderItem(DeliveryOrderDTO deliveryOrderDTO, String distOrderNo) {
        List<DeliveryItemDTO> deliveryItemDTOList = deliveryOrderDTO.getDeliveryItemDTOList();
        List<DistOrderItemDO> distOrderItemDOS = new ArrayList<>();
        deliveryItemDTOList.forEach(deliveryItemDTO -> {
            DistOrderItemDO distOrderItemDO = new DistOrderItemDO();
            distOrderItemDO.setCityProductId(deliveryItemDTO.getCityProductId());
            distOrderItemDO.setDistOrderNo(distOrderNo);
            distOrderItemDO.setNum(deliveryItemDTO.getNum());
            distOrderItemDO.setOrderNo(deliveryOrderDTO.getOrderNo());
            distOrderItemDO.setProductNo(deliveryItemDTO.getProductNo());
            distOrderItemDO.setStoreId(deliveryOrderDTO.getStoreId());
            distOrderItemDOS.add(distOrderItemDO);
        });
        return distOrderItemDOS;
    }

    public int saveDistOrders(DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO, Date deliveryTime) {
        List<DistOrderItemDO> distOrderItemDOS = new ArrayList<>();
        List<DistOrderDO> distOrderDOS = toDistOrders(deliveryOrderMultiPkgDTO, distOrderItemDOS, deliveryTime);
        return fdpTransactionTemplate.execute(status -> {
            try {
                int affectRow = distOrderDAO.batchInsertDistOrder(distOrderDOS);
                distOrderItemDAO.batchInsertDistOrderItem(distOrderItemDOS);
                return affectRow;
            } catch (Exception e) {
                log.error("DistOrderRepository.batchSaveDistOrder 数据库插入数据失败,deliveryOrderMultiPkgDTO:{}", JSON.toJSONString(deliveryOrderMultiPkgDTO), e);
                throw new BizException(Errors.DB_EXECUTE_EXCEPTION);
            }
        });
    }

    /**
     * 转成多个配送单保存
     * @param deliveryOrderMultiPkgDTO
     * @param distOrderItemDOS
     * @param deliveryTime
     * @return
     */
    private List<DistOrderDO> toDistOrders(DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO, List<DistOrderItemDO> distOrderItemDOS, Date deliveryTime) {
        Integer cityId = deliveryOrderMultiPkgDTO.getCityId();
        String orderNo = deliveryOrderMultiPkgDTO.getOrderNo();
        Long storeId = deliveryOrderMultiPkgDTO.getStoreId();

        List<DistOrderDO> distOrderDOS = new ArrayList<>();
        List<DistOrderInfo> distOrderInfoList = deliveryOrderMultiPkgDTO.getDistOrderInfoList();
        distOrderInfoList.forEach(distOrderInfo -> {
            DistOrderDO distOrderDO = toDistOrder(orderNo, cityId, storeId,
                    distOrderInfo.getDeliveryInfoDTO(), deliveryTime);
            distOrderDOS.add(distOrderDO);
            DeliveryItemDTO deliveryItemDTO = distOrderInfo.getDeliveryItemDTO();
            DistOrderItemDO distOrderItemDO = new DistOrderItemDO();
            distOrderItemDO.setCityProductId(deliveryItemDTO.getCityProductId());
            distOrderItemDO.setDistOrderNo(distOrderDO.getDistOrderNo());
            distOrderItemDO.setNum(deliveryItemDTO.getNum());
            distOrderItemDO.setOrderNo(orderNo);
            distOrderItemDO.setProductNo(deliveryItemDTO.getProductNo());
            distOrderItemDO.setStoreId(storeId);
            distOrderItemDOS.add(distOrderItemDO);
        });
        return distOrderDOS;
    }

    /**
     * 全量更新配送信息
     * @param reqInfo
     * @param distOrderDO
     * @return
     */
    public int updateDistOrderMultiPkgInfo(Object reqInfo, DistOrderDO distOrderDO) {
        List<DistOrderItemDO> distOrderItemDOS = new ArrayList<>();
        List<DistOrderDO> distOrderDOS = new ArrayList<>();
        if (reqInfo instanceof DeliveryOrderMultiPkgDTO) {
            distOrderDOS = transferToDistOrders(((DeliveryOrderMultiPkgDTO) reqInfo).getDistOrderInfoList(), distOrderItemDOS, distOrderDO, false);
        } else if (reqInfo instanceof MultiPkgModifyExpressDTO) {
            distOrderDOS = transferToDistOrders(((MultiPkgModifyExpressDTO) reqInfo).getDistOrderInfoList(), distOrderItemDOS, distOrderDO, true);
        }
        List<DistOrderDO> finalDistOrderDOS = distOrderDOS;
        return fdpTransactionTemplate.execute(status -> {
            try {
                //原配送信息置为删除
                int affectRow = distOrderDAO.updateIsDeleted(distOrderDO.getOrderNo());
                //插入新配送信息
                affectRow += distOrderDAO.batchInsertDistOrder(finalDistOrderDOS);
                distOrderItemDAO.batchInsertDistOrderItem(distOrderItemDOS);
                return affectRow;
            } catch (Exception e) {
                log.error("DistOrderRepository.updateIsDeleted 数据库更新数据失败,multiPkgModifyExpressDTO:{}", JSON.toJSONString(reqInfo), e);
                throw new BizException(Errors.DB_EXECUTE_EXCEPTION);
            }
        });
    }

    private List<DistOrderDO> transferToDistOrders(List<DistOrderInfo> distOrderInfoList, List<DistOrderItemDO> distOrderItemDOS,
                                                   DistOrderDO distOrderDO, boolean isModify) {
        String orderNo = distOrderDO.getOrderNo();
        Integer cityId = distOrderDO.getCityId();
        Long storeId = distOrderDO.getStoreId();
        Integer confirmStatus = distOrderDO.getConfirmStatus();
        Integer status = distOrderDO.getStatus();
        //发货时间
        Date deliveryTime = distOrderDO.getCreateTime();
        Integer modifyTimes = distOrderDO.getModifyTimes();
        List<DistOrderDO> distOrderDOS = new ArrayList<>();
        distOrderInfoList.forEach(distOrderInfo -> {
            DistOrderDO newDistOrderDO = toDistOrder(orderNo, cityId, storeId,
                    distOrderInfo.getDeliveryInfoDTO(), deliveryTime);
            if (isModify) {
                newDistOrderDO.setModifyTimes(modifyTimes + 1);
            }
            newDistOrderDO.setStatus(status);
            newDistOrderDO.setConfirmStatus(confirmStatus);
            distOrderDOS.add(newDistOrderDO);
            DeliveryItemDTO deliveryItemDTO = distOrderInfo.getDeliveryItemDTO();
            DistOrderItemDO distOrderItemDO = new DistOrderItemDO();
            distOrderItemDO.setCityProductId(deliveryItemDTO.getCityProductId());
            distOrderItemDO.setDistOrderNo(newDistOrderDO.getDistOrderNo());
            distOrderItemDO.setNum(deliveryItemDTO.getNum());
            distOrderItemDO.setOrderNo(orderNo);
            distOrderItemDO.setProductNo(deliveryItemDTO.getProductNo());
            distOrderItemDO.setStoreId(storeId);
            distOrderItemDOS.add(distOrderItemDO);
        });
        return distOrderDOS;
    }
}
