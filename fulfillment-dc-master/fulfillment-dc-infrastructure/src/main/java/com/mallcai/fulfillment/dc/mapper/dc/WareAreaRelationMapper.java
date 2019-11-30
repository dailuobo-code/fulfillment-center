package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.WareAreaRelation;
import com.mallcai.fulfillment.dc.valueobject.dc.WareAreaRelationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WareAreaRelationMapper {
    int countByExample(WareAreaRelationCriteria example);

    int deleteByExample(WareAreaRelationCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(WareAreaRelation record);

    int insertSelective(WareAreaRelation record);

    List<WareAreaRelation> selectByExampleWithRowbounds(WareAreaRelationCriteria example, RowBounds rowBounds);

    List<WareAreaRelation> selectByExample(WareAreaRelationCriteria example);

    WareAreaRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WareAreaRelation record, @Param("example") WareAreaRelationCriteria example);

    int updateByExample(@Param("record") WareAreaRelation record, @Param("example") WareAreaRelationCriteria example);

    int updateByPrimaryKeySelective(WareAreaRelation record);

    int updateByPrimaryKey(WareAreaRelation record);
}