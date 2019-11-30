package com.mallcai.fulfillment.dc.biz.service.inner;

import com.mallcai.fulfillment.dc.api.service.dc.enums.CallbackStatusEnum;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.BoxDetailRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.BoxTravelRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.ErpCallbackRequestDTO;
import com.mallcai.fulfillment.dc.mapper.dc.PackageInfoMapper;
import com.mallcai.fulfillment.dc.mapper.dc.PackageTravelMapper;
import com.mallcai.fulfillment.dc.valueobject.dc.PackageInfoDO;
import com.mallcai.fulfillment.dc.valueobject.dc.PackageTravelDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author bh.zhong
 * @date 2019/9/1 8:16 PM
 */
@Service
@Slf4j
public class ErpCallbackInnerService {

    @Resource
    private PackageInfoMapper packageInfoMapper;
    @Resource
    private PackageTravelMapper packageTravelMapper;
    @Autowired
    @Qualifier("fdcTransactionTemplate")
    private TransactionTemplate fdcTransactionTemplate;

    public Boolean callBack(ErpCallbackRequestDTO erpCallbackRequestDTO) {
        CallbackStatusEnum callbackStatusEnum = CallbackStatusEnum.getEvent(erpCallbackRequestDTO.getStatus());
        if (callbackStatusEnum == null) {
            log.error("ErpCallbackInnerService.callBack status param error,erpCallbackRequestDTO:{}",erpCallbackRequestDTO);
            return true;
        }
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        erpCallbackRequestDTO.getBoxRequestDTOS().forEach((key, value) -> {
            if (StringUtils.isBlank(key.getBoxNo()) || StringUtils.isBlank(key.getTravelNodeTime())) {
                log.error("ErpCallbackInnerService.callBack BoxTravelRequestDTO param error,erpCallbackRequestDTO:{}",erpCallbackRequestDTO);
                flag.set(true);
                return;
            }
            if (CollectionUtils.isEmpty(value)) {
                log.error("ErpCallbackInnerService.callBack BoxDetailRequestDTO param error,erpCallbackRequestDTO:{}",erpCallbackRequestDTO);
                flag.set(true);
                return;
            }
            if (!savePackage(callbackStatusEnum,key,value) && flag.get()) {
                flag.set(false);
            }
        });
        return flag.get();
    }

    private Boolean savePackage(CallbackStatusEnum callbackStatusEnum, BoxTravelRequestDTO boxTravelRequest, List<BoxDetailRequestDTO> boxDetailRequestDTOS) {
        PackageTravelDO travelDO = new PackageTravelDO();
        travelDO.setPackageNo(boxTravelRequest.getBoxNo());
        travelDO.setTravelTime(boxTravelRequest.getTravelNodeTime());
        travelDO.setStatus(callbackStatusEnum.getStatus());
        travelDO.setStatusMsg(callbackStatusEnum.getReceiveStatusMsg());
        List<PackageInfoDO> packages = new ArrayList<>();
        boxDetailRequestDTOS.forEach(boxInfo -> {
            PackageInfoDO packageInfo = new PackageInfoDO();
            packageInfo.setCityId(boxInfo.getCityId());
            packageInfo.setProduceOrderNo(boxInfo.getProduceOrderNo());
            packageInfo.setStatus(callbackStatusEnum.getStatus());
            packageInfo.setStatusMsg(callbackStatusEnum.getReceiveStatusMsg());
            packageInfo.setPackageNo(boxTravelRequest.getBoxNo());
            packageInfo.setProductNo(boxInfo.getProductNo());
            packageInfo.setProductNum(boxInfo.getProductNum());
            packages.add(packageInfo);
        });


        boolean flag = fdcTransactionTemplate.execute(status -> {
            try {
                packageTravelMapper.insertPackageTravel(travelDO);
                packageInfoMapper.batchPackageInfo(packages);
            } catch (DuplicateKeyException e) {
                return true;
            }
            return true;
        });
        return flag;
    }

}
