package com.mallcai.fulfillment.pe.infrastructure.dao;


import com.mallcai.fulfillment.pe.infrastructure.mapper.ProduceOrderDetailMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetailCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjingcheng
 */
@Slf4j
@Repository
public class ProduceOrderDetailDao {

    @Autowired
    private ProduceOrderDetailMapper produceOrderDetailMapper;

    public List<ProduceOrderDetail> selectByProduceOrderId(Long produceOrderId){

        ProduceOrderDetailCriteria produceOrderDetailCriteria = new ProduceOrderDetailCriteria();

        produceOrderDetailCriteria.createCriteria().andProduceOrderIdEqualTo(produceOrderId);

        return produceOrderDetailMapper.selectByExample(produceOrderDetailCriteria);
    }

    /**
     * 根据订单表主键ID批量查询
     * @param orderIds
     * @return
     */
    public List<ProduceOrderDetail> selectByOrderIds(List<Long> orderIds){

        ProduceOrderDetailCriteria produceOrderDetailCriteria = new ProduceOrderDetailCriteria();
        produceOrderDetailCriteria.createCriteria().andOrderIdIn(orderIds);

        return produceOrderDetailMapper.selectByExample(produceOrderDetailCriteria);
    }

    /**
     * 根据produceOrderNo批量查询
     * @param produceOrderNos
     * @return
     */
    public List<ProduceOrderDetail> selectByProduceOrderNos(List<String> produceOrderNos){

        ProduceOrderDetailCriteria produceOrderDetailCriteria = new ProduceOrderDetailCriteria();
        produceOrderDetailCriteria.createCriteria().andProduceOrderNoIn(produceOrderNos);

        return produceOrderDetailMapper.selectByExample(produceOrderDetailCriteria);
    }

//    /**
//     * 根据主键ID及初始状态更新目标状态
//     * @param id
//     * @param targetStatus
//     * @param underStatus
//     * @return
//     */
//    public boolean updateStatusByIdUnderStatus(Long id, Byte targetStatus, Byte underStatus){
//
//        InvoiceOrderDOCriteria invoiceOrderDOCriteria = new InvoiceOrderDOCriteria();
//
//        invoiceOrderDOCriteria.createCriteria().andIdEqualTo(id)
//                .andStatusEqualTo(underStatus);
//
//        InvoiceOrderDO invoiceOrderPO = new InvoiceOrderDO();
//
//        int updateCount = invoiceOrderDOMapper.updateByExampleSelective(invoiceOrderPO, invoiceOrderDOCriteria);
//
//        if (updateCount < 1){
//
//            log.error("积单明细表状态更新失败");
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * 根据主键ID及初始状态批量更新目标状态
//     * @param ids
//     * @param targetStatus
//     * @param underStatus
//     * @return
//     */
//    public boolean updateStatusByIdUnderStatus(List<Long> ids, Byte targetStatus, Byte underStatus){
//
//        InvoiceOrderDOCriteria invoiceOrderDOCriteria = new InvoiceOrderDOCriteria();
//
//        invoiceOrderDOCriteria.createCriteria().andIdIn(ids)
//                .andStatusEqualTo(underStatus);
//
//        InvoiceOrderDO invoiceOrderPO = new InvoiceOrderDO();
//
//        int updateCount = invoiceOrderDOMapper.updateByExampleSelective(invoiceOrderPO, invoiceOrderDOCriteria);
//
//        if (updateCount != ids.size()){
//
//            log.error("积单明细表状态更新失败,应更新记录数:{},实际更新记录数:{}", ids.size(), updateCount);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * 根据发货单主键ID及初始状态批量更新目标状态
//     * @param invoiceId
//     * @param targetStatus
//     * @param underStatus
//     * @return
//     */
//    public boolean updateStatusByInvoiceIdUnderStatus(Long invoiceId, Byte targetStatus, Byte underStatus){
//
//        InvoiceOrderDOCriteria invoiceOrderDOCriteria = new InvoiceOrderDOCriteria();
//
//        invoiceOrderDOCriteria.createCriteria().andInvoiceIdEqualTo(invoiceId)
//                .andStatusEqualTo(underStatus);
//
//        InvoiceOrderDO invoiceOrderPO = new InvoiceOrderDO();
//
//        int updateCount = invoiceOrderDOMapper.updateByExampleSelective(invoiceOrderPO, invoiceOrderDOCriteria);
//
//        if (updateCount < 1){
//
//            log.error("积单明细表状态更新失败");
//            return false;
//        }
//
//        return true;
//    }
}
