package com.mallcai.fulfillment.pe.biz.service.inner;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.CancleProduceRequestDTO;
import com.mallcai.fulfillment.pe.biz.service.inner.lock.DataReloadAndAggregateOrderLockService;
import com.mallcai.fulfillment.pe.infrastructure.dao.ProduceOrderDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.oms.api.OmsDispatchService;
import com.mallcai.oms.model.qo.InvalidationOrderInfo;
import com.mallcai.scm.base.CommonResult;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bh.zhong
 * @date 2019/10/17 5:07 PM
 */
@Slf4j
@Service
public class PeOrderOperateServiceInnerService {

    @Autowired
    private ProduceOrderDao produceOrderDao;

    @Resource
    private OmsDispatchService omsDispatchService;

    @Autowired
    private DataReloadAndAggregateOrderLockService dataReloadAndAggregateOrderLockService;

    public Boolean cancelProduceOrder(CancleProduceRequestDTO dto) {
        log.info("即将通知下游，进行生产单作废操作,dto:{}", JSON.toJSONString(dto));
        List<ProduceOrder> produceOrders = produceOrderDao.selectByCityIdAndwareHouseId(GroupValueEnum.FRESH_PRODUCT, dto.getWarehouseId(), dto.getCityId(), dto.getForecastDate());
        if (CollectionUtils.isEmpty(produceOrders)) {
            log.info("当前没有生产单需要通知下游进行作废,dto:{}",JSON.toJSONString(dto));
            return true;
        }
        CommonResult<String> commonResult =new CommonResult<>();
        try {
            commonResult=omsDispatchService.invalidationOrders(buildInvalidationOrderInfo(dto,produceOrders));
        }catch (Exception e){
            log.error("调用下游作废生产单超时,dto:{}",JSON.toJSONString(dto));
            return false;
        }
        return commonResult.isSuccess();
    }

    private InvalidationOrderInfo buildInvalidationOrderInfo(CancleProduceRequestDTO dto, List<ProduceOrder> produceOrders) {
        List<String> produceOrderNoList = produceOrders.stream().map(ProduceOrder::getProduceOrderNo).collect(Collectors.toList());
        InvalidationOrderInfo invalidationOrderInfo = new InvalidationOrderInfo();
        invalidationOrderInfo.setOrderNos(produceOrderNoList);
        invalidationOrderInfo.setCityId(dto.getCityId());
        invalidationOrderInfo.setWarehouseId(dto.getWarehouseId());
        invalidationOrderInfo.setOrderType(3);
        return invalidationOrderInfo;
    }
}
