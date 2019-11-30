package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.ProduceOrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.mapper.ProduceOrderMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author wangjingcheng
 */
@Slf4j
@Repository
public class ProduceOrderDao {

    @Autowired
    private ProduceOrderMapper produceOrderMapper;

    /**
     * 根据预期推单时间查询需要推单的生产单
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ProduceOrder> selectByExpectPushTime(Date startTime, Date endTime){

        ProduceOrderCriteria produceOrderCriteria = new ProduceOrderCriteria();

        produceOrderCriteria.createCriteria().andExpectPushTimeGreaterThanOrEqualTo(startTime)
                .andExpectPushTimeLessThan(endTime).
                andStatusEqualTo(ProduceOrderStatusEnum.INIT.getStatus());

        return produceOrderMapper.selectByExample(produceOrderCriteria);
    }

    /**
     * 根据城市id、仓库ID查询生产单
     * @param wareHouseId
     * @param cityId
     * @return
     */
    public List<ProduceOrder> selectByCityIdAndwareHouseId(GroupValueEnum groupValueEnum, Integer wareHouseId, Integer cityId, String expectPushDateStr){

        ProduceOrderCriteria produceOrderCriteria = new ProduceOrderCriteria();

        Date expectPushDateStart = DateUtil.dayStart(DateUtil.parseDate(expectPushDateStr));
        Date expectPushDateEnd = DateUtil.addDays(expectPushDateStart, 1);

        produceOrderCriteria.createCriteria().andGroupValueEqualTo(groupValueEnum.getGroupValue())
                .andWarehouseIdEqualTo(wareHouseId)
                .andCityIdEqualTo(cityId).andExpectPushTimeGreaterThanOrEqualTo(expectPushDateStart)
                .andExpectPushTimeLessThan(expectPushDateEnd);

        return produceOrderMapper.selectByExample(produceOrderCriteria);
    }

    /**
     * 根据城市id、仓库ID查询生产单
     * @param wareHouseId
     * @param cityId
     * @return
     */
    public int cancelProduceOrderBywareHouseIdAndCityId(GroupValueEnum groupValueEnum, Integer wareHouseId, Integer cityId, String expectPushDateStr){

        ProduceOrderCriteria produceOrderCriteria = new ProduceOrderCriteria();

        Date expectPushDateStart = DateUtil.dayStart(DateUtil.parseDate(expectPushDateStr));
        Date expectPushDateEnd = DateUtil.addDays(expectPushDateStart, 1);

        produceOrderCriteria.createCriteria().andGroupValueEqualTo(groupValueEnum.getGroupValue())
                .andWarehouseIdEqualTo(wareHouseId)
                .andCityIdEqualTo(cityId).andExpectPushTimeGreaterThanOrEqualTo(expectPushDateStart)
                .andExpectPushTimeLessThan(expectPushDateEnd);

        ProduceOrder produceOrderUpdate = new ProduceOrder();

        produceOrderUpdate.setStatus(ProduceOrderStatusEnum.CANCEL.getStatus());

        return produceOrderMapper.updateByExampleSelective(produceOrderUpdate, produceOrderCriteria);
    }

    /**
     * 根据城市id、仓库ID查询生产单
     * @param wareHouseId
     * @param cityId
     * @return
     */
    public List<ProduceOrder> ca(GroupValueEnum groupValueEnum, Integer wareHouseId, Integer cityId, String expectPushDateStr){

        ProduceOrderCriteria produceOrderCriteria = new ProduceOrderCriteria();

        Date expectPushDateStart = DateUtil.dayStart(DateUtil.parseDate(expectPushDateStr));
        Date expectPushDateEnd = DateUtil.addDays(expectPushDateStart, 1);

        produceOrderCriteria.createCriteria().andGroupValueEqualTo(groupValueEnum.getGroupValue())
                .andWarehouseIdEqualTo(wareHouseId)
                .andCityIdEqualTo(cityId).andExpectPushTimeGreaterThanOrEqualTo(expectPushDateStart)
                .andExpectPushTimeLessThan(expectPushDateEnd);

        return produceOrderMapper.selectByExample(produceOrderCriteria);
    }

    /**
     * 根据主键ID及初始状态更新目标状态
     * @param id
     * @param targetStatus
     * @param underStatus
     * @return
     */
    public boolean updateStatusByIdUnderStatus(Long id, Byte targetStatus, Byte underStatus){

        ProduceOrderCriteria produceOrderCriteria = new ProduceOrderCriteria();

        produceOrderCriteria.createCriteria().andIdEqualTo(id)
                .andStatusEqualTo(underStatus);

        ProduceOrder produceOrder = new ProduceOrder();

        produceOrder.setStatus(targetStatus);

        int updateCount = produceOrderMapper.updateByExampleSelective(produceOrder, produceOrderCriteria);

        if (updateCount < 1){

            log.error("发货单状态更新失败,id:{},targetStatu:{},underStatus:{}", id, targetStatus, underStatus);

            return false;
        }

        return true;
    }

    /**
     * 根据主键ID及初始状态更新对象
     * @param id
     * @param underStatus
     * @return
     */
    public boolean updateByIdUnderStatus(Long id, ProduceOrder produceOrderPo, Byte underStatus){

        ProduceOrderCriteria produceOrderCriteria = new ProduceOrderCriteria();

        produceOrderCriteria.createCriteria().andIdEqualTo(id)
                .andStatusEqualTo(underStatus);

        int updateCount = 0;
        try {
            updateCount = produceOrderMapper.updateByExampleSelective(produceOrderPo, produceOrderCriteria);
        } catch (Exception e) {

            log.error("更新生产单状态异常,id:{},targetStatus:{}, underStatus:{}", id, produceOrderPo.getStatus(), underStatus);
            throw new BizException();
        }

        if (updateCount < 1){

            log.error("发货单状态更新失败,id:{},underStatus:{}", id, underStatus);

            return false;
        }

        return true;
    }
}
