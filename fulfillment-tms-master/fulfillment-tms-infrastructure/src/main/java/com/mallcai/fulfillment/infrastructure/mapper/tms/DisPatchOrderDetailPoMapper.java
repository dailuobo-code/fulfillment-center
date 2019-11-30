package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.DisPatchOrderDetailPo;
import com.mallcai.fulfillment.infrastructure.object.tms.DisPatchOrderDetailPoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DisPatchOrderDetailPoMapper {
    long countByExample(DisPatchOrderDetailPoCriteria example);

    int deleteByExample(DisPatchOrderDetailPoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(DisPatchOrderDetailPo record);

    int insertSelective(DisPatchOrderDetailPo record);

    List<DisPatchOrderDetailPo> selectByExampleWithRowbounds(DisPatchOrderDetailPoCriteria example, RowBounds rowBounds);

    List<DisPatchOrderDetailPo> selectByExample(DisPatchOrderDetailPoCriteria example);

    DisPatchOrderDetailPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DisPatchOrderDetailPo record, @Param("example") DisPatchOrderDetailPoCriteria example);

    int updateByExample(@Param("record") DisPatchOrderDetailPo record, @Param("example") DisPatchOrderDetailPoCriteria example);

    int updateByPrimaryKeySelective(DisPatchOrderDetailPo record);

    int updateByPrimaryKey(DisPatchOrderDetailPo record);
}