package com.mallcai.fulfillment.dc.api.service.dc.operate;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareAreaInfo;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareListReq;

import java.util.List;

public interface WareStoreService {

    /**
     * 查询仓区域关系列表
     */
    PlainResult<List<WareAreaInfo>> queryWareAreaList(WareListReq req);


    /**
     * 新增,修改仓区域关系列表
     */
    PlainResult<?> addOrUpdateWareArea(WareAreaInfo req);


    /**
     * 删除仓-区域关系列表
     */
    PlainResult<?> deleteWareArea(Integer wareHouseId);

}
