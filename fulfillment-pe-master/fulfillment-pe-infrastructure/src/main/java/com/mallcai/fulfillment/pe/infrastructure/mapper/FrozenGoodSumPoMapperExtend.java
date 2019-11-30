package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.FrozenGoodSumPo;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.FrozenGoodSumPoCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FrozenGoodSumPoMapperExtend {
  int batchInsertSelective(List<FrozenGoodSumPo> list);
}