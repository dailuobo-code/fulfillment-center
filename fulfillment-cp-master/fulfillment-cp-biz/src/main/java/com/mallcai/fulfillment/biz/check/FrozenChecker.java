package com.mallcai.fulfillment.biz.check;

import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * 交易对账比较器,左右两边的值比较全部相等,为比对成功,否则为失败
 */
@Component("FrozenChecker")
@Slf4j
public class FrozenChecker implements Checker {

  @Override
  public void check(CheckContext context) {
    Table data=context.getResult();
    Set<String> keys= data.rowKeySet();

    //放入对比关系
    for (String key : keys) {
      String left = String.valueOf(data.get(key, Constants.TBL_LEFT_V));
      String right= String.valueOf(data.get(key, Constants.TBL_RIGHT_V));
      boolean result=StringUtils.equals(left,right);
      if(!result){
        log.error("存在冻品有数量不一致问题,cityId:{},ERP数量:{},履约数量:{}",key,left,right);
      }
      data.put(key,Constants.TBL_CHECK_RESULT,result);
    }

  }
}
