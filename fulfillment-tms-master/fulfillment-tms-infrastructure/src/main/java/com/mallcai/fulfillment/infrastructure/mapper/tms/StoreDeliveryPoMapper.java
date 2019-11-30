package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.StoreDeliveryPo;
import com.mallcai.fulfillment.infrastructure.object.tms.StoreDeliveryPoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StoreDeliveryPoMapper {
    long countByExample(StoreDeliveryPoCriteria example);

    int deleteByExample(StoreDeliveryPoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(StoreDeliveryPo record);

    int insertSelective(StoreDeliveryPo record);

    List<StoreDeliveryPo> selectByExampleWithRowbounds(StoreDeliveryPoCriteria example, RowBounds rowBounds);

    List<StoreDeliveryPo> selectByExample(StoreDeliveryPoCriteria example);

    StoreDeliveryPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StoreDeliveryPo record, @Param("example") StoreDeliveryPoCriteria example);

    int updateByExample(@Param("record") StoreDeliveryPo record, @Param("example") StoreDeliveryPoCriteria example);

    int updateByPrimaryKeySelective(StoreDeliveryPo record);

    int updateByPrimaryKey(StoreDeliveryPo record);
}