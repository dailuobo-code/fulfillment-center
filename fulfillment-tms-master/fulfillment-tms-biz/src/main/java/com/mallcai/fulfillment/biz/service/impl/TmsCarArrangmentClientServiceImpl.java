package com.mallcai.fulfillment.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.fulfillment.biz.dto.TmsOrderVO;
import com.mallcai.fulfillment.biz.dto.TmsSkuVO;
import com.mallcai.fulfillment.biz.enums.WareHouseTypeEnum;
import com.mallcai.fulfillment.biz.service.StoreServiceClient;
import com.mallcai.fulfillment.biz.service.TmsCarArrangmentClientService;
import com.mallcai.fulfillment.biz.util.OkHttpDomain;
import com.mallcai.manager.common.exception.BizException;
import com.mallcai.shop.api.service.response.StoreRespDTO;
import com.mallcai.shop.api.service.response.WareHouseRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18 16:09:42
 */
@Slf4j
@Service
public class TmsCarArrangmentClientServiceImpl implements TmsCarArrangmentClientService {

    private static final String activeProfile = "fulfill";

    private static final String waveNoSplitFlag = "-";

    @Autowired
    private StoreServiceClient storeServiceClient;

    @Override
    public TmsOrderVO sendCarArrangmentCommand(Integer storeId) {

        TmsOrderVO tmsOrderVO = buildPlannedTmsOrderByStore(storeId);

        String waveNumber = tmsOrderVO.getWaybillNO();
        try {
            // 拼接URL
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.now();
            String localTime = df.format(time);
            String host = "https://tmsapi.dailuobo.com";
            String api = "/Other/InsOrderInfoAHCC";
            String appKey = "C476928B-F3B7-4DAC-8AB6-628B73870456";
            StringBuilder url = new StringBuilder();
            url.append(host).append(api);
            String pJson = "[" + JSON.toJSONString(tmsOrderVO) + "]";
            url.append("?appkey=").append(appKey).append("&timestamp=").append(localTime);
            log.info("pJson:{}", pJson);
            log.info("推送TMS URL:{}", url);
            // 发送
            String respStr = OkHttpDomain.postJsonParams(url.toString(), pJson);
            // 解析回参
            log.info("waveNumber:{},TMS返回：{}", waveNumber, respStr);
            JSONObject jsonObject = JSON.parseObject(respStr);
            JSONObject result = jsonObject.getJSONObject("result");
            String code = result.getString("code");
            // 发送消息到tms成功 解析tms返回信息
            if ("1".equals(code)) {
                log.info("waveNumber:{}订单推送TMS成功", waveNumber);
            } else {
                // 消息推送失败
                log.error("waveNumber:{}订单推送TMS失败, code：{}", waveNumber, code);
                log.error("resp:{}", respStr);
                throw new BizException();
            }
        } catch (Exception e) {
            log.error("waveNumber:{}订单推送TMS失败！：{}", waveNumber, e);
            throw e;
        }

        return tmsOrderVO;
    }

    /**
     * @Description: 组装首次发送当日发货订单到TMS(计划)
     * @Author: YangYong
     * @Date: 2019/7/3 15:32
     * @param: storeNo
     * @param: pickupDate
     * @param: list
     * @Return: com.mallcai.wms.webapi.service.oub.vo.TmsOrderVO
     */
    private TmsOrderVO buildPlannedTmsOrderByStore(Integer storeId) {

        // 组装order
        TmsOrderVO vo = new TmsOrderVO();
        List<StoreRespDTO> storeRespDTOS = storeServiceClient.searchStoreByIds(Sets.newHashSet(storeId));

        StoreRespDTO storeRespDTO = storeRespDTOS.get(0);


        // 收货方区域编码(门店编号)
        vo.setReceiverAreaCode(storeRespDTO.getStoreNo() == null ? "" : storeRespDTO.getStoreNo().toString());
        vo.setReceiverName(storeRespDTO.getManager());
        vo.setReceiverPhone(storeRespDTO.getTelephone());

        // 发货方信息
        WareHouseRespDTO wareHouseRespDTO = getFreshWareHouse(storeRespDTO.getWareHouseDTOS());
        vo.setShipperAreaCode(wareHouseRespDTO.getCode());
        vo.setShipperName(wareHouseRespDTO.getName());
        vo.setDispatchPointName(wareHouseRespDTO.getCode());

        StringBuilder stringBuilder = new StringBuilder();

        String waybillNO = stringBuilder
                .append(activeProfile).append(waveNoSplitFlag)
                .append(wareHouseRespDTO.getCode()).append(waveNoSplitFlag)
                .append(storeRespDTO.getStoreNo()).append(waveNoSplitFlag)
                .append("20").append(waveNoSplitFlag)
                .append(DateUtils.formatDate(DateUtils.addDay(new Date(),1)))
                .toString();

        // 组成单号
        vo.setWaybillNO(waybillNO);

        List<TmsSkuVO> tmsSkuVOList = Lists.newArrayList();
        TmsSkuVO tmsSkuVO = new TmsSkuVO();
        tmsSkuVO.setGoodsNo("0000");
        tmsSkuVO.setGoodsName("其他1");
        //单位是吨 所以需要除以1000
        tmsSkuVO.setTotalWeight(new BigDecimal("1"));
        tmsSkuVO.setTotalVolume(BigDecimal.ONE);
        // 之前备注里放的是吨数 现在放一个默认值
        vo.setWaybillRemark("1");
        tmsSkuVO.setQty(1);
        tmsSkuVOList.add(tmsSkuVO);
        vo.setGoodsList(tmsSkuVOList);
        vo.setStorageLocation("1");
        vo.setAllocationName("");
        vo.setAllocationPhone("");

        return vo;
    }

    private WareHouseRespDTO getFreshWareHouse(List<WareHouseRespDTO> wareHouseDTOS) {

        for (WareHouseRespDTO wareHouseRespDTO : wareHouseDTOS) {

            if (WareHouseTypeEnum.WARE_HOUSE_FRESH.getType().equals(wareHouseRespDTO.getType())) {

                return wareHouseRespDTO;
            }
        }

        return null;
    }
}
