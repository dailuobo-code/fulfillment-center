package com.mallcai.fulfillment.dc.biz.service.inner;

import com.alibaba.fastjson.JSON;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchaseDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchaseForecastInfoListDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchasePredictRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.dc.biz.service.producer.SendPePurchaseForecastMsg;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.utils.BeanCopierUtils;
import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.fulfillment.dc.dao.dc.PurchaseDAO;
import com.mallcai.fulfillment.dc.dao.bigData.WarehouseSalesForecastDAO;
import com.mallcai.fulfillment.dc.valueobject.dc.PurchaseCriteria;
import com.mallcai.fulfillment.dc.valueobject.bigData.WarehouseSalesForecastCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.PurchaseDO;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoQueryDTO;
import com.mallcai.fulfillment.pe.api.service.operate.PurchaseOperateService;
import com.mallcai.scm.base.CommonResult;
import com.mallcai.service.purchase.api.IPredictDataService;
import com.mallcai.service.purchase.dto.predict.PredictAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import sun.security.krb5.internal.crypto.KeyUsage;

/**
 * 接收订单
 *
 * @author Liu Yang
 * @description 大数据采购预测订单处理
 * @date 2019/9/28 4:30 PM
 */
@Service
@Slf4j
public class ReceivePurchaseForecastInnerService {

  @Autowired
  @Qualifier("fdcTransactionTemplate")
  private TransactionTemplate fdcTransactionTemplate;
  @Resource
  private IPredictDataService iPredictDataService;
  @Resource
  private PurchaseDAO purchaseDAO;
  @Resource
  private WarehouseSalesForecastDAO warehouseSalesForecastDAO;
  @Autowired
  private SendPePurchaseForecastMsg sendPePurchaseForecastMsg;
  @Resource
  private PurchaseOperateService purchaseOperateService;
  @Resource
  private GoodsGrayRouterService goodsGrayRouterService;
  @Resource
  private RedisService redisService;

  /**
   * 一次最大传送消息数量
   */
  private final Integer MAX_PROCESS_NUM = 100;
  /**
   * 第一次插入
   */
  private final Integer IS_FIRST_TIME = 0;

  public Boolean receiveMsg(PurchasePredictRequestDTO purchasePredictRequestDTO,
      Byte forecastType) {
    if (!goodsGrayRouterService.isNeedRoute(purchasePredictRequestDTO.getCityId())) {
      log.warn("本城市没有开通货品开关，无法进行数据导入,sortingRequestDTO:{}",
          JSON.toJSONString(purchasePredictRequestDTO));
      return true;
    }
    boolean checkResult = bigDataPurchasePredictParamChecker(purchasePredictRequestDTO);
    if (!checkResult) {
      log.error("参数校验失败！purchasePredictRequestDTO:{}",
          JSON.toJSONString(purchasePredictRequestDTO));
      return true;
    }
    //当前消息中的version大于value中的version，则作废之前的数据，重新插入
    CommonResult result = checkAllowUpdatePredit(purchasePredictRequestDTO, forecastType);
    if (!result.isSuccess()) {
      log.error("采购预测数据不可重入，ERP已经生成采购单" + JSON.toJSONString(purchasePredictRequestDTO));
      return true;
    }
    String redisKey = bulidRedisKey(purchasePredictRequestDTO, forecastType);
    String value = redisService.get(redisKey);
    if (null != value) {
      Integer existVersion = Integer.valueOf(value);
      if (existVersion < purchasePredictRequestDTO.getVersion()) {
        log.info("当前系统中已存在版本低于消息中数据版本，将进行数据作废后更新操作！purchasePredictRequestDTO:{}",JSON.toJSONString(purchasePredictRequestDTO));
        List<PurchaseDO> existPurchaseList = getExistPurchaseList(purchasePredictRequestDTO,
            forecastType);
        //作废指定城市和仓库的数据
        List<Long> purchaseIdList=new ArrayList<>();
        try {
          if(CollectionUtils.isNotEmpty(existPurchaseList)){
            purchaseIdList= existPurchaseList.stream().map(PurchaseDO::getId)
                .collect(Collectors.toList());
            purchaseDAO.cancelPurchase(purchaseIdList);
          }
          com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO purchaseForecastInfoListMsg = new com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO();
          purchaseForecastInfoListMsg.setCityId(purchasePredictRequestDTO.getCityId());
          purchaseForecastInfoListMsg.setWarehouseId(purchasePredictRequestDTO.getWarehouseId());
          purchaseForecastInfoListMsg.setForecastType(forecastType);
          if (com.mallcai.fulfillment.pe.common.constants.Constants.PREDICT_T_2
              .equals(forecastType)) {
            purchaseForecastInfoListMsg
                .setCategoryType(purchasePredictRequestDTO.getCategoryType().byteValue());
          }
          purchaseForecastInfoListMsg
              .setForecastDate(purchasePredictRequestDTO.getForecastDate());
          purchaseForecastInfoListMsg.setVersion(purchasePredictRequestDTO.getVersion());
          PlainResult plainResult = purchaseOperateService
              .cancelPurchase(purchaseForecastInfoListMsg);
          if (!plainResult.isSuccess()) {
            log.error("删除PE层采购预测数据失败,purchaseForecastInfoListMsg:{}",
                JSON.toJSONString(purchaseForecastInfoListMsg));
            return false;
          }
        } catch (Exception e) {
          log.error("删除已有采购单异常,purchaseIdList:{}", JSON.toJSONString(purchaseIdList), e);
          return false;
        }

        if (!executeBatchInsertPurchaseForecastList(purchasePredictRequestDTO, forecastType,
            redisKey)) {
          return false;
        }
        log.info("预测数据更新操作成功！purchasePredictRequestDTO:{}",JSON.toJSONString(purchasePredictRequestDTO));
      } else {
        //当前消息中的version小于等于value中的version，则忽略掉该消息直接返回true
        log.error("当前消费成功的版本为：{},收到消息的版本低于当前库中的版本，消息将被忽略！purchasePredictRequestDTO:{}",
            redisService.get(redisKey), JSON.toJSONString(purchasePredictRequestDTO));
        return true;
      }
    } else {
      //如果redis没这个key，视为没有成功消费过该天该仓的数据。则查询履约库中有没有该天该仓的数据，如果有，则比较后插入或者更新，如果没有该天的数据，则直接插入。成功消费时记录或者更新redis
      log.info("预测数据开始首次插入操作！purchasePredictRequestDTO:{}",JSON.toJSONString(purchasePredictRequestDTO));
      if (!executeBatchInsertPurchaseForecastList(purchasePredictRequestDTO, forecastType,
          redisKey)) {
        return false;
      }
      log.info("预测数据首次插入操作成功！purchasePredictRequestDTO:{}",JSON.toJSONString(purchasePredictRequestDTO));
    }
    return true;
  }

  private String bulidRedisKey(PurchasePredictRequestDTO purchasePredictRequestDTO,
      Byte forecastType) {
    String redisKey =
        purchasePredictRequestDTO.getCityId() + "_" + purchasePredictRequestDTO.getWarehouseId()
            + "_" + forecastType + "_" +
            purchasePredictRequestDTO.getCategoryType() + "_" + purchasePredictRequestDTO
            .getForecastDate();
    return redisKey;
  }

  /**
   * 检查上一版本的采购数据是否都已经推单完成
   *
   * @param purchasePredictRequestDTO
   * @param forecastType
   * @return
   */
  public boolean checkIfPeProcessing(PurchasePredictRequestDTO purchasePredictRequestDTO,
      Byte forecastType) {
    PurchaseForecastInfoQueryDTO purchaseForecastInfoQueryDTO = new PurchaseForecastInfoQueryDTO();
    purchaseForecastInfoQueryDTO.setCityId(purchasePredictRequestDTO.getCityId());
    purchaseForecastInfoQueryDTO.setWarehouseId(purchasePredictRequestDTO.getWarehouseId());
    purchaseForecastInfoQueryDTO
        .setCategoryType(purchasePredictRequestDTO.getCategoryType().byteValue());
    purchaseForecastInfoQueryDTO.setVersion(purchasePredictRequestDTO.getVersion() - 1);
    purchaseForecastInfoQueryDTO.setForecastType(forecastType);
    purchaseForecastInfoQueryDTO.setForecastDate(purchasePredictRequestDTO.getForecastDate());
    PlainResult plainResult = purchaseOperateService
        .getPurchaseStatusByCondition(purchaseForecastInfoQueryDTO);
    if (!plainResult.isSuccess()) {
      log.error("当前PE系统中仍存在上一版本的数据，请稍后人工调用大数据更新接口，当前大数据接口参数为:{}",
          JSON.toJSONString(purchasePredictRequestDTO));
      return true;
    } else {
      return false;
    }
  }


  public boolean executeBatchInsertPurchaseForecastList(
      PurchasePredictRequestDTO purchasePredictRequestDTO, Byte forecastType, String redisKey) {
    //此处的count为需要分几批插入数据
    int forecastNum = getWarehouseSalesForecastNum(purchasePredictRequestDTO, forecastType);
    int executeNum = forecastNum / MAX_PROCESS_NUM + 1;
    if (executeNum == 0) {
      log.error("receiveOrder error大数据采购数据不存在！purchasePredictRequestDTO:{}",
          JSON.toJSONString(purchasePredictRequestDTO));
      return false;
    }
    //批量处理，一次100
    for (int i = 0; i < executeNum; i++) {
      //查询大数据线上库并赋值到purchase表
      List<PurchaseDO> purchaseDOList = warehouseSalesForecastDAO
          .selectByLimit(purchasePredictRequestDTO.getCityId(),
              purchasePredictRequestDTO.getWarehouseId(),
              DateUtil.parseDate(purchasePredictRequestDTO.getForecastDate()),
              forecastType, purchasePredictRequestDTO.getCategoryType(),
              i * MAX_PROCESS_NUM,
              MAX_PROCESS_NUM, purchasePredictRequestDTO.getVersion());
      if(CollectionUtils.isEmpty(purchaseDOList)){
        break;
      }
      //转换、存储到DC数据库中
      //执行插入或者更新
      if (executePurchaseSave(purchaseDOList)) {
        try {
          //组装PE消息数据
          PurchaseForecastInfoListDTO purchaseForecastInfoListMsg = assemblePurchaseForecastInfoListMsg(
              purchasePredictRequestDTO, purchaseDOList, i, forecastType, redisKey, forecastNum);
          //调用PE，进行PE层面的数据插入
          log.info("发送大数据采购预测消息开始,城市ID:{}，仓库ID:{}，版本号:{},批次:{}，品类:{},预测类型:{}",
              purchasePredictRequestDTO.getCityId(), purchasePredictRequestDTO.getWarehouseId(),
              purchasePredictRequestDTO.getVersion(), i,
              purchasePredictRequestDTO.getCategoryType(), forecastType);
          sendPePurchaseForecastMsg.sendBatchMsg(purchaseForecastInfoListMsg);
          log.info("发送大数据采购预测消息结束,城市ID:{}，仓库ID:{}，版本号:{},批次:{}，品类:{},预测类型:{}",
              purchasePredictRequestDTO.getCityId(), purchasePredictRequestDTO.getWarehouseId(),
              purchasePredictRequestDTO.getVersion(), i,
              purchasePredictRequestDTO.getCategoryType(), forecastType);
        } catch (Exception e) {
          log.error("发送大数据采购预测消息开始,城市ID:{}，仓库ID:{}，版本号:{},批次:{}，品类:{},预测类型:{}",
              purchasePredictRequestDTO.getCityId(), purchasePredictRequestDTO.getWarehouseId(),
              purchasePredictRequestDTO.getVersion(), i,
              purchasePredictRequestDTO.getCategoryType(), forecastType);
          //DC层插入数据回滚，当前处理逻辑为删除本次插入的数据
          List<Long> purchaseIdList = purchaseDOList.stream().map(PurchaseDO::getId)
              .collect(Collectors.toList());
          try {
            purchaseDAO.cancelPurchase(purchaseIdList);
          } catch (Exception ex) {
            log.error("删除已有采购单异常,purchaseIdList:{}", JSON.toJSONString(purchaseIdList), ex);
          }
        }
      } else {
        log.error("插入采购单异常,purchaseIdList:{}", JSON.toJSONString(purchaseDOList));
        return false;
      }
    }
    return true;
  }

  public CommonResult checkAllowUpdatePredit(PurchasePredictRequestDTO purchasePredictRequestDTO,
      Byte forecastType) {
    PredictAttribute predictAttribute = new PredictAttribute();
    predictAttribute.setCityId(purchasePredictRequestDTO.getCityId());
    predictAttribute
        .setPredictDate(DateUtil.parseDate(purchasePredictRequestDTO.getForecastDate()));
    predictAttribute.setForecastType(forecastType.intValue());
    predictAttribute.setProductBatchCategory(purchasePredictRequestDTO.getCategoryType());
    predictAttribute.setWarehouseId(purchasePredictRequestDTO.getWarehouseId());
    //传的是即将插入的版本号
    predictAttribute.setVersion(purchasePredictRequestDTO.getVersion());
    return iPredictDataService.checkAllowUpdatePredit(predictAttribute);
  }

  public boolean bigDataPurchasePredictParamChecker(
      PurchasePredictRequestDTO purchasePredictRequestDTO) {
    if (Objects.isNull(purchasePredictRequestDTO.getCityId()) || Objects
        .isNull(purchasePredictRequestDTO.getWarehouseId()) ||
        Objects.isNull(purchasePredictRequestDTO.getForecastDate()) || Objects
        .isNull(purchasePredictRequestDTO.getVersion())) {
      log.error("大数据采购预测消息数据参数为空，purchasePredictRequestDTO:{}",
          JSON.toJSONString(purchasePredictRequestDTO));
      return false;
    }
    Date currentDate = DateUtil.todayStart();
    Date forecastDate = DateUtil.parseDate(purchasePredictRequestDTO.getForecastDate());
    if (DateUtil.isBefore(forecastDate, currentDate)) {
      log.error("大数据采购预测消息数据有误，预测时间小于当天时间！purchasePredictRequestDTO:{}",
          JSON.toJSONString(purchasePredictRequestDTO));
    }
    return true;
  }

  public int getWarehouseSalesForecastNum(PurchasePredictRequestDTO purchasePredictRequestDTO,
      Byte forecastType) {
    WarehouseSalesForecastCriteria example = new WarehouseSalesForecastCriteria();
    if (Constants.PREDICT_T.equals(forecastType)) {
      //补采预测
      example.createCriteria().andCityIdEqualTo(purchasePredictRequestDTO.getCityId()).
          andWarehouseIdEqualTo(purchasePredictRequestDTO.getWarehouseId())
          .andForecastDateEqualTo(DateUtil.parseDate(purchasePredictRequestDTO.getForecastDate()))
          .andForecastTypeEqualTo(forecastType);
    } else if (Constants.PREDICT_T_2.equals(forecastType)) {
      //采购预测
      example.createCriteria().andCityIdEqualTo(purchasePredictRequestDTO.getCityId()).
          andWarehouseIdEqualTo(purchasePredictRequestDTO.getWarehouseId())
          .andForecastDateEqualTo(DateUtil.parseDate(purchasePredictRequestDTO.getForecastDate()))
          .andForecastTypeEqualTo(forecastType).
          andLv1ClassifyTypeEqualTo(purchasePredictRequestDTO.getCategoryType());
    }
    return warehouseSalesForecastDAO.countByExample(example);
  }

  public List<PurchaseDO> getExistPurchaseList(PurchasePredictRequestDTO purchasePredictRequestDTO,
      Byte forecastType) {
    //需要根据采购还是补采，查询已有的数据
    List<PurchaseDO> result = new ArrayList<>();
    PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
    if (Constants.PREDICT_T.equals(forecastType)) {
      //补采预测
      purchaseCriteria.createCriteria().andCityIdEqualTo(purchasePredictRequestDTO.getCityId()).
          andWarehouseIdEqualTo(purchasePredictRequestDTO.getWarehouseId())
          .andForecastDateEqualTo(DateUtil.parseDate(purchasePredictRequestDTO.getForecastDate()))
          .andForecastTypeEqualTo(forecastType)
          .andIsDeletedEqualTo(Constants.NOT_DELETED);
    } else if (Constants.PREDICT_T_2.equals(forecastType)) {
      //采购预测
      purchaseCriteria.createCriteria().andCityIdEqualTo(purchasePredictRequestDTO.getCityId()).
          andWarehouseIdEqualTo(purchasePredictRequestDTO.getWarehouseId())
          .andForecastDateEqualTo(DateUtil.parseDate(purchasePredictRequestDTO.getForecastDate()))
          .andForecastTypeEqualTo(forecastType)
          .andCategoryTypeEqualTo(purchasePredictRequestDTO.getCategoryType().byteValue()).
          andIsDeletedEqualTo(Constants.NOT_DELETED);
    }
    result = purchaseDAO.selectByExample(purchaseCriteria);
    return result;
  }


  public PurchaseForecastInfoListDTO assemblePurchaseForecastInfoListMsg(
      PurchasePredictRequestDTO purchasePredictRequestDTO, List<PurchaseDO> purchaseDOList,
      int count, Byte forecastType, String redisKey, int totalCount) {
    PurchaseForecastInfoListDTO purchaseForecastInfoListMsg = new PurchaseForecastInfoListDTO();
    purchaseForecastInfoListMsg.setCityId(purchasePredictRequestDTO.getCityId());
    purchaseForecastInfoListMsg.setVersion(purchasePredictRequestDTO.getVersion());
    purchaseForecastInfoListMsg.setCount(count);
    purchaseForecastInfoListMsg.setWarehouseId(purchasePredictRequestDTO.getWarehouseId());
    purchaseForecastInfoListMsg.setForecastType(forecastType);
    purchaseForecastInfoListMsg.setForecastDate(purchasePredictRequestDTO.getForecastDate());
    purchaseForecastInfoListMsg.setTotalCount(totalCount);
    purchaseForecastInfoListMsg.setRedisKey(redisKey);
    if (forecastType.equals(Constants.PREDICT_T_2)) {
      purchaseForecastInfoListMsg
          .setCategoryType(purchasePredictRequestDTO.getCategoryType().byteValue());
    }
    List<PurchaseDTO> purchaseDTOList = new ArrayList<>();
    for (PurchaseDO purchaseDO : purchaseDOList) {
      PurchaseDTO purchaseDTO = new PurchaseDTO();
      BeanCopierUtils.copyProperties(purchaseDO, purchaseDTO);
      purchaseDTOList.add(purchaseDTO);
    }
    purchaseForecastInfoListMsg.setPurchaseDOList(purchaseDTOList);
    return purchaseForecastInfoListMsg;
  }

  public List<PurchaseDO> processPurchaseList(List<PurchaseDO> purchaseDOList,
      List<PurchaseDO> existPurchaseList) {
    List<PurchaseDO> result = new ArrayList<>();
    for (PurchaseDO tmp : purchaseDOList) {
      boolean flag = false;
      for (PurchaseDO exist : existPurchaseList) {
        if (exist.getCityId().equals(tmp.getCityId()) && exist.getCityProductId()
            .equals(tmp.getCityProductId()) && exist.getWarehouseId().equals(tmp.getWarehouseId())
            && exist.getForecastDate().equals(tmp.getForecastDate())) {
          flag = true;
        }
      }
      if (!flag) {
        result.add(tmp);
      }
    }
    return result;
  }

  public Boolean executePurchaseSave(List<PurchaseDO> purchaseDOLists) {
    boolean flag = true;
    for (PurchaseDO purchaseDO : purchaseDOLists) {
      if (checkIfNotExistPurchaseDO(purchaseDO)) {
        try {
          purchaseDAO.insert(purchaseDO);
        } catch (Exception e) {
          log.error("插入采购数据时发生错误,purchaseDO:{}", JSON.toJSONString(purchaseDO), e);
          flag = false;
        }
      }
    }
    return flag;
  }

  public boolean checkIfNotExistPurchaseDO(PurchaseDO purchaseDO) {
    PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
    purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseDO.getCityId())
        .andWarehouseIdEqualTo(purchaseDO.getWarehouseId()).
        andCityProductIdEqualTo(purchaseDO.getCityProductId())
        .andForecastDateEqualTo(purchaseDO.getForecastDate())
        .andForecastTypeEqualTo(purchaseDO.getForecastType())
        .andVersionEqualTo(purchaseDO.getVersion())
        .andIsDeletedEqualTo(Constants.NOT_DELETED);
    int count = purchaseDAO.countSpecialByExample(purchaseCriteria);
    if (count == 0) {
      return true;
    }
    return false;
  }


}
