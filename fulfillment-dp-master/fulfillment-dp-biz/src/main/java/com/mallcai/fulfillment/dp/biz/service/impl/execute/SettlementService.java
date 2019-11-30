package com.mallcai.fulfillment.dp.biz.service.impl.execute;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.common.exception.BizException;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dao.SettlementLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dao.SettlementWarnLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject.SettlementLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject.SettlementWarnLogDO;
import com.mallcai.fulfillment.dp.api.common.dto.SettlementLogDTO;
import com.mallcai.fulfillment.dp.api.enums.SettlementStatusEnum;
import com.mallcai.fulfillment.dp.api.enums.SettlementTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author yl
 * @description Settlement
 * @date 2019-07-20
 */
@Slf4j
@Service
public class SettlementService {

    @Resource
    private SettlementLogDAO settlementLogDAO;
    @Resource
    private SettlementWarnLogDAO settlementWarnLogDAO;

    /**
     * 初始化交付记录表
     */
    public SettlementLogDTO initConfirmSettlement(String orderId,DeliveredDetailDTO deliveredDetailDTO) {
        SettlementLogDTO settlementLogDTO = new SettlementLogDTO();
        SettlementLogDO settlementLogDO = settlementLogDAO.selectByOrderId(orderId);
        if (Objects.isNull(settlementLogDO)) {
            settlementLogDO = new SettlementLogDO();
            settlementLogDO.setOrderId(orderId);
            settlementLogDO.setSource(deliveredDetailDTO.getOperateSource());
            settlementLogDO.setCreateTime(new Date());
            settlementLogDO.setStatus(SettlementStatusEnum.PROCESSING.getCode());
            settlementLogDO.setType(SettlementTypeEnum.PICK_UP_LOG.getCode());
            settlementLogDO.setUserId(deliveredDetailDTO.getUserId().longValue());
            settlementLogDO.setRequestData(JSONObject.toJSONString(deliveredDetailDTO));
            settlementLogDO.setDeviceId(deliveredDetailDTO.getDeviceId());
            settlementLogDO.setCityId(deliveredDetailDTO.getCityId());
            settlementLogDO.setStoreId(deliveredDetailDTO.getStoreId().intValue());
            settlementLogDO.setPlatformSource(1);
            settlementLogDAO.insertSelective(settlementLogDO);
            deliveredDetailDTO.setOperateSettlement(true);
        }
        BeanUtils.copyProperties(settlementLogDO, settlementLogDTO);
        return settlementLogDTO;

    }

    /**
     * 更新 SettlementLog状态和type
     */
    public boolean updateSettlement(String orderId, Integer type, Integer status,
                                    DeliveredDetailDTO deliveredDetail) {

        SettlementLogDO settlementLogDO = new SettlementLogDO();
        settlementLogDO.setType(type);
        settlementLogDO.setStatus(status);
        settlementLogDO.setOrderId(orderId);
        int count = settlementLogDAO.updateBySettlement(settlementLogDO);
        if (count <= 0) {
            throw new BizException("更新settlement 失败");
        }
        if (Objects.nonNull(deliveredDetail)) {
            SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();
            settlementLog.setType(type);
            settlementLog.setStatus(status);
            deliveredDetail.setSettlementLog(settlementLog);
        }
        return true;

    }

    public void insertWarnLog(DeliveredDetailDTO deliveredDetail, Integer type) {
        try {
            SettlementWarnLogDO settlementWarnLogDO = new SettlementWarnLogDO();
            settlementWarnLogDO.setDeviceId(deliveredDetail.getDeviceId());
            settlementWarnLogDO.setOrderId(deliveredDetail.getDeliveredOrderList().get(0).getOrderId());
            settlementWarnLogDO.setType(type);
            settlementWarnLogDO.setCreateTime(new Date());
            settlementWarnLogDO.setUserId(Long.valueOf(deliveredDetail.getUserId()));
            settlementWarnLogDO.setOperateId(Long.valueOf(deliveredDetail.getOperatorId()));
            settlementWarnLogDO.setCityId(deliveredDetail.getCityId());
            settlementWarnLogDO.setStoreId(deliveredDetail.getStoreId().intValue());
            settlementWarnLogDAO.insertSelective(settlementWarnLogDO);
        } catch (Exception e) {
            log.error("插入settlementWarnLog报错", e);
        }

    }
}
