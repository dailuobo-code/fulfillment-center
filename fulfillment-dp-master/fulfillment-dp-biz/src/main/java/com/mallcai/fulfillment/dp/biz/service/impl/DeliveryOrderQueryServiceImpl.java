package com.mallcai.fulfillment.dp.biz.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.DeliveryOrderQueryService;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.response.*;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.DeliveryOrderQueryServiceParamChecker;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.MultiPkgServiceChecker;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dao.DistOrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject.DistOrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-09-18 15:26
 */
@Service
@Slf4j
public class DeliveryOrderQueryServiceImpl implements DeliveryOrderQueryService {

  @Resource
  private DeliveryOrderQueryServiceParamChecker deliveryOrderQueryServiceParamChecker;
  @Resource
  private DistOrderDAO distOrderDAO;
  @ConfigValue(key = "/app_fulfillment/common/express.company.list")
  private JSONObject expressJson;

  @Override
  public PlainResult<DistOrderDTO> queryByOrderNo(DeliveryOrderQueryDTO deliveryOrderQueryDTO) {
    //校验参数
    deliveryOrderQueryServiceParamChecker.checkExpressInfoQuery(deliveryOrderQueryDTO);
      List<DistOrderDO> distOrderDOS = distOrderDAO.selectByOrderNo(deliveryOrderQueryDTO.getOrderNo());
      if (CollectionUtils.isNotEmpty(distOrderDOS) && distOrderDOS.size() > 1) {
          return PlainResultBuilder.fail(0, "单运单查询，存在多运单物流信息");
      }
      DistOrderDO distOrderDO = null;
      if (CollectionUtils.isNotEmpty(distOrderDOS)) {
          distOrderDO = distOrderDOS.get(0);
      }
      DistOrderDTO distOrderDTO=null;
    if (distOrderDO != null) {
       distOrderDTO=buildDistOrderDTO(distOrderDO);
    }
    return PlainResultBuilder.success(distOrderDTO);
  }

  public DistOrderDTO buildDistOrderDTO(DistOrderDO distOrderDO){
    DistOrderDTO distOrderDTO=new DistOrderDTO();
    distOrderDTO.setDistNo(distOrderDO.getDistOrderNo());
    distOrderDTO.setOrderNo(distOrderDO.getOrderNo());
    distOrderDTO.setDeliveryTime(distOrderDO.getCreateTime());
    ExpressInfo expressInfo=new ExpressInfo();
    expressInfo.setExpressName(distOrderDO.getExpressName());
    expressInfo.setExpressNo(distOrderDO.getExpressNo());
    distOrderDTO.setExpressInfo(expressInfo);
    return distOrderDTO;
  }

    @Override
    public PlainResult<DistOrderMultiPkgDTO> queryMultiPkgByOrderNo(DeliveryOrderQueryDTO deliveryOrderQueryDTO) {
        PlainResult plainResult = deliveryOrderQueryServiceParamChecker.checkExpressInfoQuery(deliveryOrderQueryDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }
        List<DistOrderDO> distOrderDOs = distOrderDAO.selectByOrderNo(deliveryOrderQueryDTO.getOrderNo());
        if (CollectionUtils.isEmpty(distOrderDOs)) {
            return PlainResultBuilder.success(null);
        }

        //提取每个DistOrder中的物流信息
        List<DistOrderExpressInfo> distOrderExpressInfos = new ArrayList<>(distOrderDOs.size());
        distOrderDOs.forEach(distOrderDO -> {
            DistOrderExpressInfo distOrderExpressInfo = buildDistOrderInfo(distOrderDO);
            distOrderExpressInfos.add(distOrderExpressInfo);
        });

        DistOrderMultiPkgDTO distOrderMultiPkgDTO = new DistOrderMultiPkgDTO();
        distOrderMultiPkgDTO.setOrderNo(deliveryOrderQueryDTO.getOrderNo());
        distOrderMultiPkgDTO.setDistOrderInfoList(distOrderExpressInfos);

        return PlainResultBuilder.success(distOrderMultiPkgDTO);
    }

    private DistOrderExpressInfo buildDistOrderInfo(DistOrderDO distOrderDO) {
        DistOrderExpressInfo distOrderExpressInfo = new DistOrderExpressInfo();
        distOrderExpressInfo.setDistNo(distOrderDO.getDistOrderNo());
        ExpressInfo expressInfo = new ExpressInfo();
        expressInfo.setExpressName(distOrderDO.getExpressName());
        expressInfo.setExpressNo(distOrderDO.getExpressNo());
        distOrderExpressInfo.setExpressInfo(expressInfo);
        distOrderExpressInfo.setDeliveryTime(distOrderDO.getCreateTime());
        return distOrderExpressInfo;
    }

        @Override
        public PlainResult<BatchDistOrderMultiPkgDTO> batchQueryMultiPkgByOrderNos(BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO) {
            PlainResult plainResult = MultiPkgServiceChecker.checkBatchQueryInfo(batchDeliveryOrderQueryDTO);
            if (!plainResult.isSuccess()) {
                return plainResult;
            }

            List<String> orderNos = batchDeliveryOrderQueryDTO.getOrderNos();

            List<DistOrderMultiPkgDTO> distOrderMultiPkgDTOS = new ArrayList<>(orderNos.size());

            List<DistOrderDO> distOrderDOS = distOrderDAO.selectByOrderNos(orderNos);
            if (CollectionUtils.isNotEmpty(distOrderDOS)) {
                Map<String, List<DistOrderDO>> distOrderDOMap =
                        distOrderDOS.stream().collect(Collectors.groupingBy(DistOrderDO::getOrderNo));
                distOrderDOMap.forEach((orderNo, distOrderDOs) -> {
                    //提取每个DistOrder中的物流信息
                    List<DistOrderExpressInfo> distOrderExpressInfos = new ArrayList<>(distOrderDOs.size());
                    distOrderDOs.forEach(distOrderDO -> {
                        DistOrderExpressInfo distOrderExpressInfo = buildDistOrderInfo(distOrderDO);
                        distOrderExpressInfos.add(distOrderExpressInfo);
                    });

                    DistOrderMultiPkgDTO distOrderMultiPkgDTO = new DistOrderMultiPkgDTO();
                    distOrderMultiPkgDTO.setOrderNo(orderNo);
                    distOrderMultiPkgDTO.setDistOrderInfoList(distOrderExpressInfos);

                    distOrderMultiPkgDTOS.add(distOrderMultiPkgDTO);
                });
            }

            BatchDistOrderMultiPkgDTO batchDistOrderMultiPkgDTO = new BatchDistOrderMultiPkgDTO();
            batchDistOrderMultiPkgDTO.setDistOrderMultiPkgDTOList(distOrderMultiPkgDTOS);
            return PlainResultBuilder.success(batchDistOrderMultiPkgDTO);
        }

        @Override
        public PlainResult<ExpressCompanyListDTO> searchExpressCompany() {
            ExpressCompanyListDTO expressCompanyListDTO = expressJson.toJavaObject(ExpressCompanyListDTO.class);
            if (expressCompanyListDTO == null) {
                log.error("物流公司信息未配置");
                expressCompanyListDTO = new ExpressCompanyListDTO();
            }
            return PlainResultBuilder.success(expressCompanyListDTO);
        }
}
