package com.mallcai.fulfillment.biz.reshandle;

import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component("TradeResultHandler")
@Slf4j
public class TradeResultHandler implements ResultHandler{
    @Override
    public void handleResult(CheckContext checkContext) {
        log.info("即将开始进行出错数据反查...checkNo={}",checkContext.getCheckNo());
        Table<Object,String,List<String>> keyToSourceTable=checkContext.getKeyToSourceTable();
        Table<String,String,Object> result=checkContext.getResult();
        List<Object> errorKeyList=checkContext.getErrorKeyList();
        if(CollectionUtils.isNotEmpty(errorKeyList)){
            StringBuffer errorKeyBuffer=new StringBuffer();
            errorKeyList.parallelStream().forEach(item->{errorKeyBuffer.append(String.valueOf(item)+",");});
            log.error("以下cityProductId存在商品数量不一致问题：cityProductIdList:{}",errorKeyBuffer.toString());
            for(Object cityProductId:errorKeyList){
                List<String> orderIdListFromTrade=keyToSourceTable.get(Integer.valueOf(String.valueOf(cityProductId)), Constants.TBL_LEFT_V);
                List<String> orderIdListFromPe=keyToSourceTable.get(Integer.valueOf(String.valueOf(cityProductId)),Constants.TBL_RIGHT_V);
                List<String> tradeMoreThanPeList = getMoreList(orderIdListFromTrade,orderIdListFromPe);
                if(CollectionUtils.isNotEmpty(tradeMoreThanPeList)){
                    StringBuffer stringBuffer=new StringBuffer();
                    tradeMoreThanPeList.parallelStream().forEach(item->{stringBuffer.append(item);});
                    log.error("针对于cityProductId:{},交易侧:{},履约侧:{},以下订单号交易存在且履约不存在:{}",cityProductId,result.get(String.valueOf(cityProductId),
                            Constants.TBL_LEFT_V)==null?0:result.get(String.valueOf(cityProductId), Constants.TBL_LEFT_V),result.get(String.valueOf(cityProductId),
                            Constants.TBL_RIGHT_V)==null?0:result.get(String.valueOf(cityProductId), Constants.TBL_RIGHT_V),stringBuffer.toString());
                }
                List<String> peMoreThanTradeList = getMoreList(orderIdListFromPe,orderIdListFromTrade);
                if(CollectionUtils.isNotEmpty(peMoreThanTradeList)){
                    StringBuffer stringBuffer=new StringBuffer();
                    peMoreThanTradeList.parallelStream().forEach(item->{stringBuffer.append(item);});
                    log.error("针对于cityProductId:{},交易侧:{},履约侧:{},以下订单号履约存在且交易不存在:{}",cityProductId,result.get(String.valueOf(cityProductId),
                            Constants.TBL_LEFT_V)==null?0:result.get(String.valueOf(cityProductId), Constants.TBL_LEFT_V),result.get(String.valueOf(cityProductId),
                            Constants.TBL_RIGHT_V)==null?0:result.get(String.valueOf(cityProductId), Constants.TBL_RIGHT_V),stringBuffer.toString());
                }
            }
        }
        log.info("出错数据反查结束...checkNo={}",checkContext.getCheckNo());
    }

    /**
     * 获取SourceList比TargetList多的项
     * @param sourceList
     * @param targetList
     * @return
     */
    public List<String> getMoreList(List<String> sourceList,List<String> targetList){
        if(CollectionUtils.isNotEmpty(targetList)&&CollectionUtils.isNotEmpty(sourceList)){
            return sourceList.parallelStream().filter(item -> !targetList.contains(item)).collect(toList());
        }else{
            return sourceList;
        }
    }
}
