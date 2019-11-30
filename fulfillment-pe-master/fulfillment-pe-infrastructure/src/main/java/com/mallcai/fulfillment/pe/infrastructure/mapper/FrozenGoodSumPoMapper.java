package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.FrozenGoodSumPo;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.FrozenGoodSumPoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FrozenGoodSumPoMapper {
    long countByExample(FrozenGoodSumPoCriteria example);

    int deleteByExample(FrozenGoodSumPoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(FrozenGoodSumPo record);

    int insertSelective(FrozenGoodSumPo record);

    List<FrozenGoodSumPo> selectByExampleWithRowbounds(FrozenGoodSumPoCriteria example, RowBounds rowBounds);

    List<FrozenGoodSumPo> selectByExample(FrozenGoodSumPoCriteria example);

    FrozenGoodSumPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FrozenGoodSumPo record, @Param("example") FrozenGoodSumPoCriteria example);

    int updateByExample(@Param("record") FrozenGoodSumPo record, @Param("example") FrozenGoodSumPoCriteria example);

    int updateByPrimaryKeySelective(FrozenGoodSumPo record);

    int updateByPrimaryKey(FrozenGoodSumPo record);
}