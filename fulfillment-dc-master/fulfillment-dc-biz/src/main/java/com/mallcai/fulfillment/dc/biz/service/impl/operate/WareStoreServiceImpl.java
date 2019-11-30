package com.mallcai.fulfillment.dc.biz.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareAreaInfo;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareListReq;
import com.mallcai.fulfillment.dc.biz.service.inner.WareAreaRelaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mallcai.fulfillment.dc.api.service.dc.operate.WareStoreService;
import java.util.List;

@Service("wareStoreService")
@Slf4j
public class WareStoreServiceImpl implements WareStoreService{

    @Autowired
    WareAreaRelaService wareAreaRelaService;

    @Override
    public PlainResult<List<WareAreaInfo>> queryWareAreaList(WareListReq req) {

        List<WareAreaInfo> wareAreaInfos =null;
        try {
            wareAreaInfos = wareAreaRelaService.queryInfoList(req);
        }catch (Exception e){
            log.error("查询仓-区域关系失败.info:{}", JSON.toJSONString(req));
            return PlainResult.fail("查询仓区域关系失败");
        }
        return PlainResult.ok(wareAreaInfos);
    }

    @Override
    public PlainResult<?> addOrUpdateWareArea(WareAreaInfo req) {

        try {
            wareAreaRelaService.updateRelaInfo(req);
        }catch (Exception e){
            log.error("新增/修改仓-区域关系失败.info:{}", JSON.toJSONString(req));
            return PlainResult.fail("新增/修改仓区域关系失败");
        }
        return PlainResult.ok("");
    }

    @Override
    public PlainResult<?> deleteWareArea(Integer wareHouseId) {

        try {
            wareAreaRelaService.deleteRelaInfo(wareHouseId);
        }catch (Exception e){
            log.error("删除仓-区域关系失败.info:{}", wareHouseId);
            return PlainResult.fail("删除仓区域关系失败");
        }
        return PlainResult.ok("");
    }
}
