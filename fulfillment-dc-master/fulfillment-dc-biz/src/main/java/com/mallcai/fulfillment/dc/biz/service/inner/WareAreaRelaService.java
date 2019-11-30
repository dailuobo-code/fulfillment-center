package com.mallcai.fulfillment.dc.biz.service.inner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareAreaInfo;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareListReq;
import com.mallcai.fulfillment.dc.biz.service.utils.BaseBeanUtil;
import com.mallcai.fulfillment.dc.common.enums.WareAreaStatus;
import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.mapper.dc.WareAreaRelationMapperExtend;
import com.mallcai.fulfillment.dc.valueobject.dc.WareAreaRelation;
import com.mallcai.fulfillment.dc.valueobject.dc.WareAreaRelationCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WareAreaRelaService {

    @Autowired
    WareAreaRelationMapperExtend mapper;

    /**
     * 查询列表
     * @param req
     * @return
     */
    public List<WareAreaInfo> queryInfoList(WareListReq req){

        WareAreaRelationCriteria example=new WareAreaRelationCriteria();
        WareAreaRelationCriteria.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(WareAreaStatus.VALID.getCode());
        if(req.getStart()!=null){
            criteria.andCreateTimeGreaterThanOrEqualTo(req.getStart());
        }
        if(req.getEnd()!=null){
            criteria.andCreateTimeLessThan(req.getEnd());
        }
        if(req.getWareHouseId()!=null){
            criteria.andWarehouseIdEqualTo(req.getWareHouseId());
        }
        RowBounds rowBounds=RowBounds.DEFAULT;
        if(req.getPage()!=null && req.getSize()!=null){
            rowBounds=new RowBounds((req.getPage()-1)*req.getSize(),req.getSize());
        }

        List<WareAreaRelation> wareAreaRelations = mapper.selectByExampleWithRowbounds(example, rowBounds);

        return wareAreaRelations.stream()
                .map(p->{
                    WareAreaInfo wareAreaInfo = BaseBeanUtil.convertObject(p, WareAreaInfo.class);
                    wareAreaInfo.setAreas(JSONObject.parseObject(p.getAreaList(),new TypeReference<List<Integer>>(){}));
                    return wareAreaInfo;
                }).collect(Collectors.toList());
    }


    /**
     * 更新仓和区域的关系
     * @param info
     */
    public void updateRelaInfo(WareAreaInfo info){

        if(info.getWarehouseId()==null){
            throw new BizException("新建(更新)仓区域关系的时候,wareHouseId不能为null");
        }
        WareAreaRelation record = BaseBeanUtil.convertObject(info, WareAreaRelation.class);
        if(!CollectionUtils.isEmpty(info.getAreas())){
            record.setAreaList(JSON.toJSONString(info.getAreas()));
        }

        //先判断是否有仓信息
        WareAreaRelation oldRecord = mapper.selectByWareHouse(info.getWarehouseId(),WareAreaStatus.VALID.getCode());
        if(oldRecord==null){
            mapper.insertSelective(record);
        }else{
            record.setId(oldRecord.getId());
            mapper.updateByPrimaryKeySelective(record);
        }

    }

    /**
     * 删除仓和区域的关系
     * @param wareHouseId
     */
    public void deleteRelaInfo(Integer wareHouseId){
        WareAreaRelation wareAreaRelation = mapper.selectByWareHouse(wareHouseId,WareAreaStatus.VALID.getCode());
        if(wareAreaRelation==null){
            throw new BizException("删除仓区域关系,记录不存在");
        }

        wareAreaRelation.setStatus(WareAreaStatus.CANCEL.getCode());

        mapper.updateByPrimaryKeySelective(wareAreaRelation);
    }


}
