package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.DisPatchOrderPo;
import com.mallcai.fulfillment.infrastructure.object.tms.DisPatchOrderPoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DisPatchOrderPoMapper {
    long countByExample(DisPatchOrderPoCriteria example);

    int deleteByExample(DisPatchOrderPoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(DisPatchOrderPo record);

    int insertSelective(DisPatchOrderPo record);

    List<DisPatchOrderPo> selectByExampleWithRowbounds(DisPatchOrderPoCriteria example, RowBounds rowBounds);

    List<DisPatchOrderPo> selectByExample(DisPatchOrderPoCriteria example);

    DisPatchOrderPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DisPatchOrderPo record, @Param("example") DisPatchOrderPoCriteria example);

    int updateByExample(@Param("record") DisPatchOrderPo record, @Param("example") DisPatchOrderPoCriteria example);

    int updateByPrimaryKeySelective(DisPatchOrderPo record);

    int updateByPrimaryKey(DisPatchOrderPo record);
}