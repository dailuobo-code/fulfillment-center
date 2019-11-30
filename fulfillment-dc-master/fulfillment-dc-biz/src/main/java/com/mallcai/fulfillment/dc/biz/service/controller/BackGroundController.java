package com.mallcai.fulfillment.dc.biz.service.controller;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.DeliverGoodsService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchasePredictRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.SortingRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceivePurchaseForecastInnerService;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveSortingForecastInnerService;
import com.mallcai.fulfillment.dc.biz.service.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.dao.bigData.StoreSkuForecastDAO;
import com.mallcai.fulfillment.dc.dao.dc.OrderFlowStepDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 后门口子，用于触发一些任务之类的
 * @author: Liu Yang
 * @create: 2019-10-14 15:28:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/fulfillment/dc/background/")
public class BackGroundController {
    @Resource
    private DeliverGoodsService deliverGoodsService;
    @Resource
    private ReceivePurchaseForecastInnerService receivePurchaseForecastInnerService;
    @Resource
    private ReceiveSortingForecastInnerService receiveSortingForecastInnerService;
    @Resource
    private StoreSkuForecastDAO storeSkuForecastDAO;

    @Autowired
    private OrderFlowStepDAO orderFlowStepDAO;

    @RequestMapping(value = "orderpush/task")
    public String orderPushTask(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return startDate+"+"+endDate;
    }
    @RequestMapping(value = "importMgrCsv")
    public PlainResult<?> importMgrCsv(@RequestParam("startDate") String importDate) {
        return deliverGoodsService.reInvokePeImportMgrService(importDate);
    }

    @RequestMapping(value = "importPurchaseData")
    public PlainResult<?> importPurchaseData(@RequestBody PurchasePredictRequestDTO purchasePredictRequestDTO) {
        boolean result= receivePurchaseForecastInnerService.receiveMsg(purchasePredictRequestDTO,
            Constants.PREDICT_T_2);
        if(!result){
            return PlainResult.fail("失败");
        }
        return PlainResultBuilder.success(true);
    }

    @RequestMapping(value = "importSortingData")
    public PlainResult<?> importSortingData(@RequestBody SortingRequestDTO sortingRequestDTO) {
        String requestId = sortingRequestDTO.getCityId()+"_"+sortingRequestDTO.getWarehouseId();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_SORTING_PREDICT+sortingRequestDTO.getCityId()+"_"+sortingRequestDTO.getWarehouseId();
        boolean result= receiveSortingForecastInnerService.receiveMsg(sortingRequestDTO,lockKey,requestId);
        if(!result){
            return PlainResult.fail("失败");
        }
        return PlainResultBuilder.success(true);
    }

    @RequestMapping(value = "dataCount")
    public String dataCount() {
        int forecastResultNum = storeSkuForecastDAO.countAll();
        return "total sum="+forecastResultNum;
    }

    @RequestMapping(value = "flow/step/status")
    public boolean updateOrderStatus(@RequestParam("flowStepIds") String flowStepIds, @RequestParam("targetStatus") String targetStatus,
                                     @RequestParam("underStatus") String underStatus){

        log.info("后门触发集单,flowStepIds:{},targetStatus:{},underStatus:{}", flowStepIds, targetStatus, underStatus);
        List<Long> orderIds = Arrays.asList(flowStepIds.split(Constants.SYMBOL_COMMA)).stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());

        int count = 0;
        for (Long id : orderIds){
            try {

                orderFlowStepDAO.updateStatusById(id, Byte.valueOf(targetStatus), Byte.valueOf(underStatus));

            } catch (Exception e) {
                log.error("后门触发更新订单状态失败,id:{}", id, e);
                continue;
            }

            count++;
        }

        return count == orderIds.size();
    }


}
