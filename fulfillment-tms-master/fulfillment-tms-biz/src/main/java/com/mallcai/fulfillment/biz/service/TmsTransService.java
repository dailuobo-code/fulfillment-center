package com.mallcai.fulfillment.biz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.fulfillment.biz.enums.DeliveryStatusEnum;
import com.mallcai.fulfillment.biz.enums.GroupValueEnum;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DispatchCommandMsg;
import com.mallcai.fulfillment.infrastructure.mapper.tms.DeliveryRecordPoMapper;
import com.mallcai.fulfillment.infrastructure.mapper.tms.GoodDeliveryPoMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.tms.StoreDeliveryPoMapper;
import com.mallcai.fulfillment.infrastructure.object.tms.DeliveryRecordPo;
import com.mallcai.fulfillment.infrastructure.object.tms.GoodDeliveryPo;
import com.mallcai.fulfillment.infrastructure.object.tms.StoreDeliveryPo;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.WareTransInfo;
import com.mallcai.wms.common.base.CommonResult;
import com.mallcai.wms.oub.client.dto.TmsOrderDTO;
import com.mallcai.wms.oub.client.dto.TmsSkuDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TmsTransService {

    @Autowired
    private DeliveryRecordPoMapper deliveryRecordPoMapper;

    @Autowired
    private StoreDeliveryPoMapper storeDeliveryPoMapper;

    @Autowired
    private GoodDeliveryPoMapperExtend goodDeliveryPoMapperExtend;

    public DeliveryRecordPo initOrderCommand(DispatchCommandMsg commandMsg, TmsOrderDTO order, Date pickDate){
        DeliveryRecordPo recordPo  =new DeliveryRecordPo();
        recordPo.setCommandId(commandMsg.getCommandNo());
        recordPo.setCommandType(commandMsg.getCommandType().name());
        JSONObject commandInfo = JSON.parseObject(commandMsg.getData());
        if(commandInfo.containsKey("storeId")) {
            recordPo.setStoreId(commandInfo.getInteger("storeId"));
        }
        recordPo.setPickupDate(pickDate);
        recordPo.setWaybillNo(order.getWaybillNO());
        if(order!=null && !CollectionUtils.isEmpty(order.getGoodsList())) {
            TmsSkuDTO tmsSkuDTO = order.getGoodsList().get(0);
            recordPo.setTotalCnt(Long.valueOf(tmsSkuDTO.getQty()));
            recordPo.setTotalWeight(tmsSkuDTO.getTotalWeight());
            recordPo.setTotalVolume(tmsSkuDTO.getTotalVolume());
        }
        recordPo.setWmsParams(JSON.toJSONString(order));
        recordPo.setStatus(DeliveryStatusEnum.INIT.name());
        deliveryRecordPoMapper.insertSelective(recordPo);
        return recordPo;
    }

    public void updateOrderWmsRsp(DeliveryRecordPo recordPo, CommonResult rsp){
        recordPo.setWmsRsp(JSON.toJSONString(rsp));
        recordPo.setWmsReqTime(new Date());
        if(rsp.isSuccessResult()) {
            recordPo.setStatus(DeliveryStatusEnum.SEND_WMS.name());
        }else{
            recordPo.setStatus(DeliveryStatusEnum.SEND_FAIL.name());
        }
        deliveryRecordPoMapper.updateByPrimaryKeySelective(recordPo);

    }

    public void initWareStore(String commandNo, String waveNo,GroupValueEnum value,List<WareTransInfo> wareInfos){

        for (WareTransInfo wareInfo : wareInfos) {
            StoreDeliveryPo store =new StoreDeliveryPo();
            store.setCommandId(commandNo);
            store.setGoodType(value.name());
            store.setPickupDate(wareInfo.getPickupDate());
            store.setReceiver(wareInfo.getReceiver());
            store.setStoreId(wareInfo.getStoreId());
            store.setStoreNo(wareInfo.getStoreNo());
            store.setWarehouseId(wareInfo.getWareId());
            store.setWarehouseCode(wareInfo.getWareCode());
            store.setSoldCount(wareInfo.getSoldCount());
            store.setWaybillNo(waveNo);
            storeDeliveryPoMapper.insertSelective(store);
            List<GoodDeliveryPo> goodList = wareInfo.getGoodsList().stream().map(p -> {
                GoodDeliveryPo good = new GoodDeliveryPo();
                good.setWaybillNo(waveNo);
                good.setStoreId(wareInfo.getStoreId());
                good.setSkuCode(p.getSkuId());
                good.setSkuName(p.getSkuName());
                good.setSkuCount(p.getSkuCount());
                good.setSkuUnit(p.getSkuUnit());
                good.setSkuVolume(p.getSkuVolume());
                good.setSkuWeight(p.getSkuWeight());
                good.setStoreId(store.getStoreId());
                good.setSkuType(value.name());
                good.setSkuCount(p.getSkuCount());
                return good;
            }).collect(Collectors.toList());

            goodDeliveryPoMapperExtend.batchInsertSelective(goodList);
        }

    }


}
