package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.GoodDeliveryPo;
import com.mallcai.fulfillment.infrastructure.object.tms.GoodDeliveryPoCriteria;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportLinePo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface GoodDeliveryPoMapperExtend extends GoodDeliveryPoMapper{
    int batchInsertSelective(List<GoodDeliveryPo> list);
}