package com.mallcai.fulfillment.biz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.biz.dto.TmsOrderVO;
import com.mallcai.fulfillment.biz.enums.DeliveryStatusEnum;
import com.mallcai.fulfillment.biz.enums.GroupValueEnum;
import com.mallcai.fulfillment.biz.rocketmq.provide.DcTransNoticeProducer;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DispatchCommandMsg;
import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import com.mallcai.fulfillment.infrastructure.dao.DeliveryRecordPoDAO;
import com.mallcai.fulfillment.infrastructure.object.tms.DeliveryRecordPo;
import com.mallcai.fulfillment.pe.api.service.query.TransDetailService;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.WareTransInfo;
import com.mallcai.wms.common.base.CommonResult;
import com.mallcai.wms.oub.client.dto.DeliveryOrderDTO;
import com.mallcai.wms.oub.client.dto.DeliveryOrderDetailDTO;
import com.mallcai.wms.oub.client.dto.TmsOrderDTO;
import com.mallcai.wms.oub.client.dto.TmsSkuDTO;
import com.mallcai.wms.oub.client.enums.OutboundBillTypeEnum;
import com.mallcai.wms.oub.client.service.TrafficPlanService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DcCommondExecService {

    @Value("${spring.profiles.active:product}")
    private String activeProfile;

    @ConfigValue(key="/app_fulfillment/common/tms.data.config")
    private Map<String,Object> tmsConfig;

//    @Reference(url = "dubbo://10.111.28.16:23240/com.mallcai.wms.oub.client.service.TrafficPlanService",version = "1.0",timeout = 30000)
    @Reference(registry = "wms",version = "1.0",timeout = 30000)
    private TrafficPlanService trafficPlanService;

//    @Reference(url = "dubbo://172.16.209.190:23001/com.mallcai.fulfillment.pe.api.service.query.TransDetailService")
    @Reference(registry = "globle",timeout = 30000)
    private TransDetailService transDetailService;

    @Autowired
    DcTransNoticeProducer dcTransNoticeProducer;

    @Autowired
    TmsTransService tmsTransService;

    @Autowired
    TmsCarArrangmentClientService tmsCarArrangmentClientService;

    @Autowired
    DeliveryRecordPoDAO deliveryRecordPoDAO;

    public void execDataCommond(DispatchCommandMsg msg){
        //取城市
        List<Integer> storeIds =Lists.newArrayList();
        if(msg.getCommandType().equals(CommandTypeEnum.TMS_BOOKING_CAR_18)){
            JSONObject commondInfo = JSON.parseObject(msg.getData());
            Integer storeId = commondInfo.getInteger("storeId");
            storeIds.add(storeId);
        }
        boolean skipFlag = (Boolean) tmsConfig.getOrDefault("skipFlag", true);
        if(CollectionUtils.isEmpty(storeIds) || skipFlag ){
            log.warn("当前指令需要执行的门店列表为空,直接不做处理!!!");
            return;
        }


        Date date = DateUtils.startDate(new Date());
        log.info("开始执行tms排车计划指令,command:{},stores:{},date:{}",msg.getCommandType(),JSON.toJSONString(storeIds),DateUtils.formatDate(date));
        String waveNumber=null;
        // 调用pe服务,获取发货量统计
        log.info("开始调用pe获取门店销售量,infos, stores:{},date:{}", JSON.toJSONString(storeIds),DateUtils.formatDate(date));
        long beg=System.currentTimeMillis();
        List<WareTransInfo> params= Lists.newArrayList();
        PlainResult<List<WareTransInfo>> standard = transDetailService.standTransPlan(storeIds, date);
        if(standard.isSuccess()){
            params.addAll(standard.getData());
            log.info("开始调用pe获取标品门店销售量,耗时:{}ms,返回销量数据:{}", System.currentTimeMillis()-beg,JSON.toJSONString(standard.getData()));

            if(waveNumber== null && !CollectionUtils.isEmpty(standard.getData())) {
                String houseCode=params.get(0).getWareCode();
                String storeNo=String.valueOf(params.get(0).getStoreNo());
                waveNumber = generateWaveNumber(houseCode, storeNo, OutboundBillTypeEnum.FINISHED_PRODUCT, date);
            }
            tmsTransService.initWareStore(msg.getCommandNo(),waveNumber,GroupValueEnum.STANDARD,standard.getData());


        }

        beg=System.currentTimeMillis();
        PlainResult<List<WareTransInfo>> fronze = transDetailService.fronzeTransPlan(storeIds, date);
        if(fronze.isSuccess()){
            params.addAll(fronze.getData());
            log.info("开始调用pe获取冻品销售量,耗时:{}ms,返回销量数据:{}", System.currentTimeMillis()-beg,JSON.toJSONString(fronze.getData()));
            if(waveNumber== null && !CollectionUtils.isEmpty(fronze.getData())) {
                String houseCode=params.get(0).getWareCode();
                String storeNo=String.valueOf(params.get(0).getStoreNo());
                waveNumber = generateWaveNumber(houseCode, storeNo, OutboundBillTypeEnum.FINISHED_PRODUCT, date);
            }
            tmsTransService.initWareStore(msg.getCommandNo(),waveNumber,GroupValueEnum.FROZEN,fronze.getData());
        }

        beg=System.currentTimeMillis();
        PlainResult<List<WareTransInfo>> freshes= transDetailService.freshTransPlan(storeIds, date);
        if(freshes.isSuccess()){
            params.addAll(freshes.getData());
            log.info("开始调用pe获取生鲜销售量,耗时:{}ms,返回销量数据:{}", System.currentTimeMillis()-beg,JSON.toJSONString(freshes.getData()));
            if(waveNumber== null && !CollectionUtils.isEmpty(freshes.getData())) {
                String houseCode=params.get(0).getWareCode();
                String storeNo=String.valueOf(params.get(0).getStoreNo());
                waveNumber = generateWaveNumber(houseCode, storeNo, OutboundBillTypeEnum.FINISHED_PRODUCT, date);
            }
            tmsTransService.initWareStore(msg.getCommandNo(),waveNumber,GroupValueEnum.FRESH,freshes.getData());
        }

        // wms 新接口
        if(CollectionUtils.isEmpty(params)){
            log.warn("当前指令没有找到对应的销售数据,返回!!!");
            return;
        }
        //沉淀 门店明细数据, 订单信息

        beg=System.currentTimeMillis();
        TmsOrderDTO order=buildTmsOrderDto(params,waveNumber);
        DeliveryRecordPo deliveryRecord = tmsTransService.initOrderCommand(msg, order, date);
        CommonResult<Boolean> result = CommonResult.failResult("调用失败");
        try {
            result = trafficPlanService.sendDeliveryInfoToTms(order, waveNumber);
            log.info("开始调用wms发送排车量,耗时:{}ms,返回信息:{}", System.currentTimeMillis()-beg,JSON.toJSONString(result));
        }catch (Exception e){
            log.error("wms接口调用失败",e);
        }

        //更新记录
        tmsTransService.updateOrderWmsRsp(deliveryRecord,result);

    }


    // 仓库全部取生鲜仓的 houseCode
    private String generateWaveNumber(String houseCode,String storeNo,OutboundBillTypeEnum type,Date pickDate) {
        return activeProfile + "-" + houseCode + "-" + storeNo + "-" + type.getCode() + "-" + DateUtils.formatDate(DateUtils.addDay(pickDate,1));
//        return activeProfile + "-" + deliveryOrder.getWarehouseCode() + "-" + deliveryOrder.getStoreNo() + "-" + deliveryOrder.getType().getCode() + "-" + deliveryOrder.getPickupDate();
    }

    private TmsOrderDTO buildTmsOrderDto(List<WareTransInfo> wareInfo,String waveNumber){
        TmsOrderDTO order=new TmsOrderDTO();
        WareTransInfo wareOne = wareInfo.get(0);
        order.setReceiverAreaCode(String.valueOf(wareOne.getStoreNo()));
        order.setReceiverName(wareOne.getReceiver());
        order.setReceiverPhone(wareOne.getTel());
        // 发货方信息
        order.setShipperAreaCode(wareOne.getWareCode());
        order.setShipperName(wareOne.getWareName());
        order.setDispatchPointName(wareOne.getWareCode());
        // 组成单号
        order.setWaybillNO(waveNumber);

        // 计算 重量,分数, 和体积
        List<TmsSkuDTO> skuList = Lists.newArrayList();
        TmsSkuDTO tmsSkuVO = new TmsSkuDTO();
        tmsSkuVO.setGoodsNo("0000");
        tmsSkuVO.setGoodsName("其他");
        //单位是吨 所以需要除以1000
        BigDecimal sumWeight = wareInfo.stream().map(WareTransInfo::getSoldWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        tmsSkuVO.setTotalWeight(sumWeight.divide(new BigDecimal(1000),6, BigDecimal.ROUND_HALF_UP));
        tmsSkuVO.setTotalVolume(BigDecimal.ONE);
        // 之前备注里放的是吨数 现在放一个默认值
        order.setWaybillRemark("1");
        BigDecimal sumCnt = wareInfo.stream().map(WareTransInfo::getSoldCount).reduce(BigDecimal.ZERO, BigDecimal::add);
        tmsSkuVO.setQty(sumCnt.intValue());
        skuList.add(tmsSkuVO);
        order.setGoodsList(skuList);
        return  order;
    }




    public void execEmptyCommond(DispatchCommandMsg command){
        Integer storeId = JSONObject.parseObject(command.getData()).getInteger("storeId");
        TmsOrderVO tmsOrderVO;
        try {
            tmsOrderVO = tmsCarArrangmentClientService.sendCarArrangmentCommand(storeId);
        } catch (Exception e) {
            log.error("排车调整指令发送异常", e);
            return;
        }

        DeliveryRecordPo recordPo = new DeliveryRecordPo();

        Date now = new Date();
        recordPo.setCommandId(command.getCommandNo());
        recordPo.setCommandType(command.getCommandType().name());
        recordPo.setStoreId(storeId);
        recordPo.setWaybillNo(tmsOrderVO.getWaybillNO());
        recordPo.setPickupDate(DateUtils.startDate(now));
        recordPo.setWmsParams(JSON.toJSONString(tmsOrderVO));
        recordPo.setTotalCnt(1L);
        recordPo.setTotalVolume(new BigDecimal("1"));
        recordPo.setTotalWeight(new BigDecimal("1"));
        recordPo.setStatus(DeliveryStatusEnum.SEND_WMS.name());
        recordPo.setWmsReqTime(now);

        deliveryRecordPoDAO.createDeliveryRecord(recordPo);
    }


    public DeliveryOrderDTO transDeliverInfo(WareTransInfo wareInfo, GroupValueEnum group){
        DeliveryOrderDTO result = new DeliveryOrderDTO();
        result.setStoreNo(wareInfo.getStoreNo());
        result.setReceiver(wareInfo.getReceiver());
        result.setTel(wareInfo.getTel());
        result.setSoldCount(wareInfo.getSoldCount());
        result.setWarehouseCode(wareInfo.getWareCode());
        result.setPickupDate(LocalDate.now().plusDays(1));
        result.setType(group.getBondtype());
        List<DeliveryOrderDetailDTO> deliverys = wareInfo.getGoodsList().stream().map(p -> {
            DeliveryOrderDetailDTO detail =new DeliveryOrderDetailDTO();
            detail.setSkuCode(p.getSkuId());
            detail.setSkuName(p.getSkuName());
            detail.setSkuCount(p.getSkuCount());
            detail.setSkuUnit(p.getSkuUnit());
            detail.setSkuVolume(p.getSkuVolume());
            detail.setSkuWeight(p.getSkuWeight());
            detail.setSkuType(group.getDeliveryType());
            return detail;
        }).collect(Collectors.toList());
        result.setDeliveryOrderDetailDtoList(deliverys);
        return result;
    }
}
