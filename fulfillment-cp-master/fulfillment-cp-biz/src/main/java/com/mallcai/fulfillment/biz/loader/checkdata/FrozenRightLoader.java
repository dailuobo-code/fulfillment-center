package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.GroupValueEnum;
import com.mallcai.fulfillment.biz.object.enums.PeOrderStatusEnum;
import com.mallcai.fulfillment.infrastructure.mapper.pe.PeOrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.pe.PeOrderExtend;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** 冻品数据加载器（履约）
 * @author Liu Yang
 * @date 2019/9/27 2:09 PM
 */
@Component("FrozenRightLoader")
@Slf4j
@Service
public class FrozenRightLoader {
  @Resource
  private PeOrderMapperExtend peOrderMapperExtend;

  public Map<String, Integer> loadResource(CheckContext checkContext) {
    log.info("FrozenRightLoader Right-LOADER-->get index:{}", JSON.toJSONString(checkContext));
    Map<String,Integer> result= Maps.newHashMap();

    String cities=(String)checkContext.getBizParam().get(Constants.PARAM_CITY);
    List<Integer> cityIdList= StringUtils.isEmpty(cities)?null:(StringUtils.equals(cities,Constants.ALL_CITY)?null: Arrays.asList(cities.split(",")).stream().
            map(w->Integer.valueOf(w)).collect(Collectors.toList()));
    List<PeOrderExtend> peOrderExtendList=peOrderMapperExtend.calFrozenTotalNumByCondition(checkContext.getBeginTime(),
            GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue(),cityIdList, PeOrderStatusEnum.PUSH_COMPLETE.getStatus());
    if(CollectionUtils.isNotEmpty(peOrderExtendList)){
      peOrderExtendList.stream().forEach(item->{
        result.put(item.getCityId()+"",item.getCalCount());
      });
    }
    return result;
  }
}
