package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.GoodDeliveryPo;
import com.mallcai.fulfillment.infrastructure.object.tms.GoodDeliveryPoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodDeliveryPoMapper {
    long countByExample(GoodDeliveryPoCriteria example);

    int deleteByExample(GoodDeliveryPoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodDeliveryPo record);

    int insertSelective(GoodDeliveryPo record);

    List<GoodDeliveryPo> selectByExampleWithRowbounds(GoodDeliveryPoCriteria example, RowBounds rowBounds);

    List<GoodDeliveryPo> selectByExample(GoodDeliveryPoCriteria example);

    GoodDeliveryPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodDeliveryPo record, @Param("example") GoodDeliveryPoCriteria example);

    int updateByExample(@Param("record") GoodDeliveryPo record, @Param("example") GoodDeliveryPoCriteria example);

    int updateByPrimaryKeySelective(GoodDeliveryPo record);

    int updateByPrimaryKey(GoodDeliveryPo record);
}