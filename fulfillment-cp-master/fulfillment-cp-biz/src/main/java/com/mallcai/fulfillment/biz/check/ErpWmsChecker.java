package com.mallcai.fulfillment.biz.check;

import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@Component("erpWmsChecker")
public class ErpWmsChecker implements Checker {
    @Override
    public void check(CheckContext context) {
        Table data = context.getResult();
        Set<String> keys= data.rowKeySet();

        //放入对比关系
        for (String key : keys) {
            BigDecimal left = (BigDecimal) data.get(key, Constants.TBL_LEFT_V);
            BigDecimal right = (BigDecimal) data.get(key, Constants.TBL_RIGHT_V);
            boolean result = (left == null && right == null) || (left != null && right != null && left.compareTo(right) == 0);
            if(!result){
                context.getErrorKeyList().add(key);
                log.error("存在商品有数量不一致问题,key[城市@仓@店@productno]:{},ERP数量:{},WMS数量:{}",key, left, right);
            }
            data.put(key, Constants.TBL_CHECK_RESULT, result);
        }
    }
}
