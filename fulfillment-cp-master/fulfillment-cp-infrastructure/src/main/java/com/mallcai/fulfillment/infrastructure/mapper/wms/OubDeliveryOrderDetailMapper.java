package com.mallcai.fulfillment.infrastructure.mapper.wms;

import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryKey;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryOrderDetail;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryOrderDetailCriteria;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OubDeliveryOrderDetailMapper {
    int countByExample(OubDeliveryOrderDetailCriteria example);

    int deleteByExample(OubDeliveryOrderDetailCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OubDeliveryOrderDetail record);

    int insertSelective(OubDeliveryOrderDetail record);

    List<OubDeliveryOrderDetail> selectByExampleWithRowbounds(OubDeliveryOrderDetailCriteria example, RowBounds rowBounds);

    List<OubDeliveryOrderDetail> selectByExample(OubDeliveryOrderDetailCriteria example);

    OubDeliveryOrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OubDeliveryOrderDetail record, @Param("example") OubDeliveryOrderDetailCriteria example);

    int updateByExample(@Param("record") OubDeliveryOrderDetail record, @Param("example") OubDeliveryOrderDetailCriteria example);

    int updateByPrimaryKeySelective(OubDeliveryOrderDetail record);

    int updateByPrimaryKey(OubDeliveryOrderDetail record);


    /**
     *
     * @param pickupDate
     * @return
     */
    List<OubDeliveryOrderDetail> listAll(LocalDate pickupDate);

    int countByCondition(@Param("keys") List<OubDeliveryKey> keys);

    List<OubDeliveryOrderDetail> selectPageByCondition(@Param("keys") List<OubDeliveryKey> keys, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    int countByPickupDate(LocalDate pickupDate);

    List<OubDeliveryOrderDetail> selectByPage(@Param("pickupDate") LocalDate pickupDate, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
}