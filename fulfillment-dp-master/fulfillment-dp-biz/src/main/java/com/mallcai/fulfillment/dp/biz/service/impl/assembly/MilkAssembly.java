package com.mallcai.fulfillment.dp.biz.service.impl.assembly;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.Response;
import com.mallcai.fulfillment.common.utils.BeanUtils;
import com.mallcai.fulfillment.common.utils.DateTimeUtils;
import com.mallcai.fulfillment.dp.api.common.dto.SupplierOutstockDTO;
import com.mallcai.fulfillment.dp.api.request.MilkQueryDTO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.MilkDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.SupplierOutstockDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto.MilkQueryParamDTO;
import com.mallcai.service.api.IGlobalService;
import com.mallcai.service.user.api.IUserService;
import com.mallcai.service.user.vo.user.AppUser;
import com.mallcai.service.user.vo.user.AppUserInfo;
import com.mallcai.service.vo.ic.common.SOACityProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description 数据组装
 * @author zhanghao
 * @date 2019-08-15 20:57:42
 */
@Component
@Slf4j
public class MilkAssembly {

    private static final Integer DEFAULT_QUERY_LIMIT = 20;

    @Resource
    private IUserService iUserService;
    @Resource
    private MilkDAO milkDAO;
    @Resource
    private IGlobalService iGlobalService;

    /**
     * 批量查询  查询参数组装
     */
    public MilkQueryParamDTO handleMilkQueryParam(MilkQueryDTO milkQueryDTO) {
        MilkQueryParamDTO milkQueryParamDTO = new MilkQueryParamDTO();
        milkQueryParamDTO.setCityId(milkQueryDTO.getCityId());
        milkQueryParamDTO.setGetStatus(milkQueryDTO.getGetStatus());
        milkQueryParamDTO.setProductNo(milkQueryDTO.getProductNo());
        milkQueryParamDTO.setStoreId(milkQueryDTO.getStoreId());
        milkQueryParamDTO.setStatus(milkQueryDTO.getStatus());
        milkQueryParamDTO.setTimeType(milkQueryDTO.getTimeType());
        //参数组装
        if (!Objects.isNull(milkQueryDTO.getAppointDay())) {
            Date startDate = DateTimeUtils.addDate(new Date(), 1 - milkQueryDTO.getAppointDay());
            milkQueryParamDTO.setEndTime(DateTimeUtils.formatDateEnd(new Date()));
            milkQueryParamDTO.setBeginTime(DateTimeUtils.formatDateStart(startDate));
        } else {
            milkQueryParamDTO.setEndTime(DateTimeUtils.formatDateEnd(milkQueryDTO.getEndTime()));
            milkQueryParamDTO.setEndTime(DateTimeUtils.formatDateStart(milkQueryDTO.getBeginTime()));
        }
        milkQueryParamDTO.setOffset(milkQueryDTO.getOffset() == null ? 0 : milkQueryDTO.getOffset());
        milkQueryParamDTO.setLimit(milkQueryDTO.getLimit() == null ? DEFAULT_QUERY_LIMIT : milkQueryDTO.getLimit());
        if(StringUtils.isNotEmpty(milkQueryDTO.getPhone())){
            AppUser appUser = iUserService.getUserByPhone(milkQueryDTO.getPhone());
            milkQueryParamDTO.setUserId(Objects.isNull(appUser) ? null : appUser.getUserId());
        }
        return milkQueryParamDTO;
    }

    public List<SupplierOutstockDTO> handleMilkAssembly(List<SupplierOutstockDO> supplierOutstockDoList,MilkQueryParamDTO milkQueryDTO){
        if(CollectionUtils.isEmpty(supplierOutstockDoList)){
            return null;
        }
        List<SupplierOutstockDTO> milkList = Lists.newArrayList();
        // 获取user信息
        List<Integer> userIds = Lists.newArrayList();
        List<String> orderIds = Lists.newArrayList();
        List<Integer> cityProductIds = Lists.newArrayList();
        supplierOutstockDoList.forEach(supplierOutstockDO -> {
            userIds.add(supplierOutstockDO.getUserId());
            orderIds.add(supplierOutstockDO.getOrderId());
            cityProductIds.add(supplierOutstockDO.getCityProductId());
        });
        List<AppUserInfo> appUserList = iUserService.getUserInfoByIdList(userIds);
        Map<Integer, AppUserInfo> userMap = Maps.newHashMap();
        appUserList.forEach(appUserInfo -> userMap.put(appUserInfo.getUserId(), appUserInfo));

        //获取订单数据
        List<SupplierOutstockDO> orderAndProductOutStockList = milkDAO.queryOrderAndProductCountList(orderIds,cityProductIds,milkQueryDTO.getEndTime());
        Map<String,Integer> getNumMap = Maps.newHashMap();
        Map<String,Integer> sumNumMap = Maps.newHashMap();
        if(CollectionUtils.isNotEmpty(orderAndProductOutStockList)){
            orderAndProductOutStockList.forEach(supplierOutstockDO -> {
                getNumMap.put(supplierOutstockDO.getOrderId()+"-"+supplierOutstockDO.getCityProductId(),supplierOutstockDO.getGetNum());
                sumNumMap.put(supplierOutstockDO.getOrderId()+"-"+supplierOutstockDO.getCityProductId(),supplierOutstockDO.getSumNum());
            });
        }
        // 商品
        Response<Map<Integer, SOACityProduct>> cityProductsByIds = iGlobalService.getCityProductsByIds(cityProductIds);
        Map<Integer, SOACityProduct> cityProduct = cityProductsByIds.getData();

        supplierOutstockDoList.forEach(supplierOutstockDO -> {
            try {
                SupplierOutstockDTO supplierOutstockDTO = BeanUtils.map(supplierOutstockDO, SupplierOutstockDTO.class);
                Integer getNum = getNumMap.get(supplierOutstockDO.getOrderId()+"-"+supplierOutstockDO.getCityProductId());
                Integer sumNum = sumNumMap.get(supplierOutstockDO.getOrderId()+"-"+supplierOutstockDO.getCityProductId());
                supplierOutstockDTO.setGetNum(getNum == null ? 0 : getNum);
                supplierOutstockDTO.setSumNum(sumNum == null ? 0 : sumNum);
                supplierOutstockDTO.setPhone(userMap.get(supplierOutstockDO.getUserId()) == null ? "" : userMap.get(supplierOutstockDO.getUserId()).getPhone());
                supplierOutstockDO.setCycleDays(supplierOutstockDO.getCycleDays() - supplierOutstockDTO.getGetNum());
                SOACityProduct soaCityProduct = cityProduct.get(supplierOutstockDO.getCityProductId());
                if(soaCityProduct != null){
                    supplierOutstockDTO.setShowName(cityProduct.get(supplierOutstockDO.getCityProductId()) == null ? "" : cityProduct.get(supplierOutstockDO.getCityProductId()).getShowName());
                    supplierOutstockDTO.setProductNo(soaCityProduct.getProductNo());
                }
                milkList.add(supplierOutstockDTO);
            } catch (Exception e) {
                log.error("查询部分数据出错：orderId：" + supplierOutstockDO.getOrderId(), e);
            }
        });
        return milkList;
    }
}
