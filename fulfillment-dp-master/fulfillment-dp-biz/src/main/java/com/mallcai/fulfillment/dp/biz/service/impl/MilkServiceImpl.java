package com.mallcai.fulfillment.dp.biz.service.impl;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.MilkService;
import com.mallcai.fulfillment.dp.api.common.dto.SupplierOutstockDTO;
import com.mallcai.fulfillment.dp.api.request.MilkPickUpDTO;
import com.mallcai.fulfillment.dp.api.request.MilkQueryDTO;
import com.mallcai.fulfillment.dp.api.response.MilkResultDTO;
import com.mallcai.fulfillment.dp.biz.service.impl.assembly.MilkAssembly;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.MilkParamChecker;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.MilkDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.ModifyLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.SupplierOutstockDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.SupplierOutstockLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto.MilkQueryParamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @description 取奶操作服务实现
 * @author zhanghao
 * @date 2019-08-15 20:26:54
 */
@Service
@Slf4j
public class MilkServiceImpl implements MilkService {

    @Resource
    private MilkParamChecker milkParamChecker;
    @Resource
    private MilkAssembly milkAssembly;
    @Resource
    private MilkDAO milkDAO;

    /**
     * 查询列表.
     * @param milkQueryDTO
     * @return
     */
    @Override
    public PlainResult<MilkResultDTO> queryMilkList(MilkQueryDTO milkQueryDTO){
        //参数校验
        PlainResult<MilkResultDTO> plainResult = milkParamChecker.checkMilkQuery(milkQueryDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }
        MilkQueryParamDTO milkQueryParamDTO = milkAssembly.handleMilkQueryParam(milkQueryDTO);
        List<SupplierOutstockDO> supplierOutstockDoList = milkDAO.queryMilkList(milkQueryParamDTO);
        Integer count = milkDAO.queryMilkListCount(milkQueryParamDTO);

        count = Objects.isNull(count) ? 0 : count;
        List<SupplierOutstockDTO> milkList = milkAssembly.handleMilkAssembly(supplierOutstockDoList,milkQueryParamDTO);
        MilkResultDTO milkResultDTO = new MilkResultDTO();
        milkResultDTO.setMilkDtoList(milkList);
        milkResultDTO.setOffset(milkQueryParamDTO.getOffset() + supplierOutstockDoList.size());
        milkResultDTO.setCount(count);
        Integer offset = milkQueryParamDTO.getOffset();
        if (Objects.isNull(offset)) {
            offset = 0;
        }
        milkResultDTO.setHasNext((offset + supplierOutstockDoList.size()) < count);
        return PlainResultBuilder.success(milkResultDTO);
    }

    /**
     * 取奶.
     * @param milkPickUpDTO
     * @return
     */
    @Override
    public PlainResult<String> pickup(MilkPickUpDTO milkPickUpDTO){
        //参数校验
        PlainResult<String> plainResult = milkParamChecker.checkMilkPickUp(milkPickUpDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }
        SupplierOutstockDO supplierOutstockDo = milkDAO.queryMilkById(milkPickUpDTO.getId());

        milkDAO.pickup(milkPickUpDTO.getId(),milkPickUpDTO.getUserId(),milkPickUpDTO.getReturnNum());

        SupplierOutstockLogDO sosl = new SupplierOutstockLogDO();
        ModifyLogDO mlb = new ModifyLogDO();
        mlb.setGetStatus(supplierOutstockDo.getStatus());
        mlb.setPickupdate(supplierOutstockDo.getPickupDate());
        String before = com.alibaba.fastjson.JSON.toJSONString(mlb);

        ModifyLogDO mla = new ModifyLogDO();
        mla.setGetStatus(1);
        mla.setPickupdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        String after = com.alibaba.fastjson.JSON.toJSONString(mla);

        sosl.setModifyAfter(after);
        sosl.setModifyBefore(before);
        sosl.setModule(4);
        sosl.setOrderId(supplierOutstockDo.getOrderId());
        sosl.setProductNo(supplierOutstockDo.getProductNo());
        sosl.setUserId(milkPickUpDTO.getUserId());

        milkDAO.insertLog(sosl);
        return PlainResultBuilder.success(null);
    }
}
