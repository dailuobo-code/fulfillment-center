package com.mallcai.fulfillment.pe.biz.service.inner.Fresh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.biz.service.bo.FreshDataCheckCityInfo;
import com.mallcai.fulfillment.pe.biz.service.enums.PeOrderSourceEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.FreshDataCheckService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.PurchaseDAO;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.PurchaseStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseCriteria;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Liu Yang
 * @description 生鲜预测命令是否下发至履约的检查时间配置实现接口
 * @date: 2019-10-28 19:48
 */
@Slf4j
@Service
public class FreshDataCheckServiceImpl implements FreshDataCheckService {

  @ConfigValue(key = "/app_fulfillment/common/fresh.check.time.config")
  private JSONObject freshConfig;

  private final String FRESH_TASK_CONFIG_KEY = "taskCheckConfig";

  private final Integer TIME_INTERVAL = 5;

  /**
   * 存在性检测
   */
  private final Integer CHECK_EXIST=1;
  /**
   * 推单检测
   */
  private final Integer CHECK_PUSH=2;

  @Autowired
  private OrderDao orderDao;

  @Autowired
  private PurchaseDAO purchaseDAO;



  @Override
  public void checkFreshData() {
    log.info("获取任务配置，content:{}", freshConfig.toJSONString());
    JSONArray jsonArray = freshConfig.getJSONArray(FRESH_TASK_CONFIG_KEY);
    for (int i = 0; i < jsonArray.size(); i++) {
      FreshDataCheckCityInfo freshDataCheckCityInfo = JSONObject
          .toJavaObject(jsonArray.getJSONObject(i), FreshDataCheckCityInfo.class);
      log.info("即将检查项目:freshDataCheckCityInfo:{}", JSON.toJSONString(freshDataCheckCityInfo));
      executeCheckExistFreshCityInfoDetail(freshDataCheckCityInfo);
      executeCheckPushFreshCityInfoDetail(freshDataCheckCityInfo);
    }
  }

  /**
   * 存在性检查
   * @param freshDataCheckCityInfo
   */
  public void executeCheckExistFreshCityInfoDetail(FreshDataCheckCityInfo freshDataCheckCityInfo) {
    if (CollectionUtils.isNotEmpty(freshDataCheckCityInfo.getCityIdList())) {
      StringBuffer errorCityIdBuffer=new StringBuffer();
      if (freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_SORTING_FRESH)) {
        for (Integer cityId : freshDataCheckCityInfo.getCityIdList()) {
          executeCheckSortingData(cityId, freshDataCheckCityInfo,errorCityIdBuffer);
        }
      } else if (freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_PURCHASE_FRESH)) {
        for (Integer cityId : freshDataCheckCityInfo.getCityIdList()) {
          executeCheckPurchaseData(cityId, freshDataCheckCityInfo,errorCityIdBuffer);
        }
      }
      //检查该时间段内的检查到的错误城市列表
      checkErrorBuffer(errorCityIdBuffer,freshDataCheckCityInfo,CHECK_EXIST);
    }
  }

  /**
   * 推单检查
   * @param freshDataCheckCityInfo
   */
  public void executeCheckPushFreshCityInfoDetail(FreshDataCheckCityInfo freshDataCheckCityInfo) {
    if (CollectionUtils.isNotEmpty(freshDataCheckCityInfo.getCityIdList())) {
      StringBuffer errorCityIdBuffer=new StringBuffer();
      if (freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_SORTING_FRESH)) {
        for (Integer cityId : freshDataCheckCityInfo.getCityIdList()) {
          executeCheckPushSortingData(cityId, freshDataCheckCityInfo,errorCityIdBuffer);
        }
      } else if (freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_PURCHASE_FRESH)) {
        for (Integer cityId : freshDataCheckCityInfo.getCityIdList()) {
          executeCheckPushPurchaseData(cityId, freshDataCheckCityInfo,errorCityIdBuffer);
        }
      }
      //检查该时间段内的检查到的错误城市列表
      checkErrorBuffer(errorCityIdBuffer,freshDataCheckCityInfo,CHECK_PUSH);
    }
  }

  public void checkErrorBuffer(StringBuffer errorCityIdBuffer,FreshDataCheckCityInfo freshDataCheckCityInfo,Integer checkErrorType){
    if(errorCityIdBuffer.length()==0){
      return;
    }
    if(checkErrorType.equals(CHECK_EXIST)){
      if(freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_SORTING_FRESH)){
        log.error("监测到分拣预测数据没有按时落库,cityIdList:{}",errorCityIdBuffer.toString());
      }else if(freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_PURCHASE_FRESH)){
        if (freshDataCheckCityInfo.getCategoryType().equals(Constants.CHECK_CATEGORY_FRUIT)) {
          log.error("监测到水果采购预测数据没有按时落库,cityIdList:{}",errorCityIdBuffer.toString());
        }else if (freshDataCheckCityInfo.getCategoryType()
            .equals(Constants.CHECK_CATEGORY_VEGETABLE)){
          log.error("监测到蔬菜采购预测数据没有按时落库,cityIdList:{}",errorCityIdBuffer.toString());
        }
      }
    }else if(checkErrorType.equals(CHECK_PUSH)){
      if(freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_SORTING_FRESH)){
        log.error("监测到分拣预测数据没有按时推单,cityIdList:{}",errorCityIdBuffer.toString());
      }else if(freshDataCheckCityInfo.getCheckType().equals(Constants.CHECK_TYPE_PURCHASE_FRESH)){
        if (freshDataCheckCityInfo.getCategoryType().equals(Constants.CHECK_CATEGORY_FRUIT)) {
          log.error("监测到水果采购预测数据没有按时推单,cityIdList:{}",errorCityIdBuffer.toString());
        }else if (freshDataCheckCityInfo.getCategoryType()
            .equals(Constants.CHECK_CATEGORY_VEGETABLE)){
          log.error("监测到蔬菜采购预测数据没有按时推单,cityIdList:{}",errorCityIdBuffer.toString());
        }
      }
    }

  }

  /**
   * 分拣数据具体存在性检查
   *
   * @param freshDataCheckCityInfo
   */
  public void executeCheckSortingData(Integer cityId,
      FreshDataCheckCityInfo freshDataCheckCityInfo,StringBuffer errorCityIdBuffer) {
    //查询当前时间和配置中到时间差是否在5分钟以内，如果在，则需要执行该条规则
    if (!checkIfNeedExecute(freshDataCheckCityInfo.getCheckTime())) {
      return;
    }
    log.info("即将检查分拣预测cityId:{}的落库情况",cityId);
    //查询是否有数据，查存在即可。
    OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
    orderDOCriteria.createCriteria().andCreateTimeGreaterThan(DateUtil.dayStart(new Date()))
        .andOrderSourceEqualTo(
            PeOrderSourceEnum.BIG.getValue()).andCityIdEqualTo(cityId);
    int count = orderDao.countSpecialByExample(orderDOCriteria);
    if (count == 0) {
      errorCityIdBuffer.append(cityId+",");
    }
  }

  /**
   * 分拣数据具体推单检查
   *
   * @param freshDataCheckCityInfo
   */
  public void executeCheckPushSortingData(Integer cityId,
      FreshDataCheckCityInfo freshDataCheckCityInfo,StringBuffer errorCityIdBuffer) {
    //查询当前时间和配置中到时间差是否在5分钟以内，如果在，则需要执行该条规则
    if (!checkIfNeedExecute(freshDataCheckCityInfo.getCheckTime())) {
      return;
    }
    log.info("即将检查分拣预测cityId:{}的推单情况",cityId);
    //查询是否有数据，查存在即可。
    OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
    List<Byte> statusList=new ArrayList<>();
    statusList.add(OrderStatusEnum.INIT.getStatus());
    statusList.add(OrderStatusEnum.AGGREGATE_PROCESS.getStatus());
    statusList.add(OrderStatusEnum.AGGREGATE_COMPLETE.getStatus());
    orderDOCriteria.createCriteria().andCreateTimeGreaterThan(DateUtil.dayStart(new Date()))
        .andOrderSourceEqualTo(
            PeOrderSourceEnum.BIG.getValue()).andCityIdEqualTo(cityId).andStatusIn(statusList);
    int count = orderDao.countSpecialByExample(orderDOCriteria);
    if (count != 0) {
      errorCityIdBuffer.append(cityId+",");
    }
  }

  /**
   * 采购数据具体检查
   *
   * @param freshDataCheckCityInfo
   */
  public void executeCheckPurchaseData(Integer cityId, FreshDataCheckCityInfo freshDataCheckCityInfo,StringBuffer errorCityIdBuffer) {
    //查询当前时间和配置中到时间差是否在5分钟以内，如果在，则需要执行该条规则
    if (!checkIfNeedExecute(freshDataCheckCityInfo.getCheckTime())) {
      return;
    }
    //查询是否有数据，查存在即可。
    PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
    if (freshDataCheckCityInfo.getCategoryType().equals(Constants.CHECK_CATEGORY_FRUIT)) {
      log.info("即将检查水果采购预测cityId:{}的落库情况",cityId);
      purchaseCriteria.createCriteria().andCreateTimeGreaterThan(DateUtil.dayStart(new Date()))
          .andForecastTypeEqualTo(Constants.PREDICT_T_2).
          andCategoryTypeEqualTo(Constants.CHECK_CATEGORY_FRUIT.byteValue()).andCityIdEqualTo(cityId).andIsDeletedEqualTo(Constants.NOT_DELETED);
      int count = purchaseDAO.countSpecialByExample(purchaseCriteria);
      if (count == 0) {
        errorCityIdBuffer.append(cityId+",");
      }
    } else if (freshDataCheckCityInfo.getCategoryType()
        .equals(Constants.CHECK_CATEGORY_VEGETABLE)) {
      log.info("即将检查蔬菜采购预测cityId:{}的落库情况",cityId);
      purchaseCriteria.createCriteria().andCreateTimeGreaterThan(DateUtil.dayStart(new Date()))
          .andForecastTypeEqualTo(Constants.PREDICT_T_2).
          andCategoryTypeEqualTo(Constants.CHECK_CATEGORY_VEGETABLE.byteValue()).andCityIdEqualTo(cityId).andIsDeletedEqualTo(Constants.NOT_DELETED);
      int count = purchaseDAO.countSpecialByExample(purchaseCriteria);
      if (count == 0) {
        errorCityIdBuffer.append(cityId+",");
      }
    }
  }


  /**
   * 采购数据具体推单检查
   *
   * @param freshDataCheckCityInfo
   */
  public void executeCheckPushPurchaseData(Integer cityId, FreshDataCheckCityInfo freshDataCheckCityInfo,StringBuffer errorCityIdBuffer) {
    //查询当前时间和配置中到时间差是否在5分钟以内，如果在，则需要执行该条规则
    if (!checkIfNeedExecute(freshDataCheckCityInfo.getCheckTime())) {
      return;
    }
    //查询是否有数据，查存在即可。
    PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
    if (freshDataCheckCityInfo.getCategoryType().equals(Constants.CHECK_CATEGORY_FRUIT)) {
      log.info("即将检查水果采购预测cityId:{}的推单情况",cityId);
      purchaseCriteria.createCriteria().andCreateTimeGreaterThan(DateUtil.dayStart(new Date()))
          .andForecastTypeEqualTo(Constants.PREDICT_T_2).
          andCategoryTypeEqualTo(Constants.CHECK_CATEGORY_FRUIT.byteValue()).andCityIdEqualTo(cityId).andStatusEqualTo(
          PurchaseStatusEnum.INIT.getStatus()).andIsDeletedEqualTo(Constants.NOT_DELETED);
      int count = purchaseDAO.countSpecialByExample(purchaseCriteria);
      if (count != 0) {
        errorCityIdBuffer.append(cityId+",");
      }
    } else if (freshDataCheckCityInfo.getCategoryType()
        .equals(Constants.CHECK_CATEGORY_VEGETABLE)) {
      log.info("即将检查蔬菜采购预测cityId:{}的推单情况",cityId);
      purchaseCriteria.createCriteria().andCreateTimeGreaterThan(DateUtil.dayStart(new Date()))
          .andForecastTypeEqualTo(Constants.PREDICT_T_2).
          andCategoryTypeEqualTo(Constants.CHECK_CATEGORY_VEGETABLE.byteValue()).andCityIdEqualTo(cityId).andStatusEqualTo(
          PurchaseStatusEnum.INIT.getStatus()).andIsDeletedEqualTo(Constants.NOT_DELETED);
      int count = purchaseDAO.countSpecialByExample(purchaseCriteria);
      if (count != 0) {
        errorCityIdBuffer.append(cityId+",");
      }
    }
  }


  public boolean checkIfNeedExecute(String executeTime) {
    try {
      SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      String fromDate = simpleFormat.format(new Date());
      String nowDate = DateUtil.formatDate(new Date());
      String toDate = simpleFormat.format(DateUtil.parseDateTime(nowDate + " " + executeTime));
      long from = simpleFormat.parse(fromDate).getTime();
      long to = simpleFormat.parse(toDate).getTime();
      int minutes = (int) (Math.abs(to - from) / (1000 * 60));
      if (minutes <= TIME_INTERVAL) {
        return true;
      }
    } catch (Exception e) {
      log.error("日期转换出错！exception:{}", e);
    }
    return false;
  }
}
