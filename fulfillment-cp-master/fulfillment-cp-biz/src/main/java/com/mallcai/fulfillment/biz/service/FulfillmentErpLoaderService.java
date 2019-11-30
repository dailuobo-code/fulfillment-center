package com.mallcai.fulfillment.biz.service;

import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.loader.BigDecimalLoader;
import com.mallcai.fulfillment.biz.loader.DataIndex;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.BizTagEnum;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import com.mallcai.manager.common.constant.Constants;
import com.mallcai.manager.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FulfillmentErpLoaderService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    NacosConfigService nacosConfigService;

    @Resource
    RedisService redisService;

    /**
     * 一次批量操作大小
     */
    private final static int MAX_PROCESS_NUM = 10000;
    /**
     * 查询间隔
     */
    private final static String PARAM_INTERVAL = "interval";


    public void loadCheckDataAndProcess(CheckContext context) {
        log.info("即将开始装载对账数据...checkNo={}", context.getCheckNo());

        TaskConfig config = context.getConfig();
        // find dataIndex
        DataIndex index = applicationContext.getBean(config.getLoadIndexBean(), DataIndex.class);
        initCheckTime(context);
        int processNum = index.getTotalCount(context);
        int executeNum = processNum / MAX_PROCESS_NUM + 1;
        int needCompareTotalCount = 0;
        for (int i = 0; i < executeNum; i++) {
            List<String> indexList = index.findIndex(context, i * MAX_PROCESS_NUM,
                    MAX_PROCESS_NUM);
            if (CollectionUtils.isEmpty(indexList)) {
                return;
     }
     context.setIndex(indexList);
     needCompareTotalCount += indexList.size();
     context.setTotalCount(needCompareTotalCount);

     //load left right data
     BigDecimalLoader leftLoader = applicationContext.getBean(config.getLeftLoaderBean(), BigDecimalLoader.class);
     BigDecimalLoader rightLoader = applicationContext.getBean(config.getRightLoaderBean(), BigDecimalLoader.class);

     Map<String, BigDecimal> leftMap = leftLoader.loadResource(context);
     Map<String, BigDecimal> rightMap = rightLoader.loadResource(context);

     //record result
     Table<String, String, Object> table = context.getResult();
     leftMap.entrySet().forEach(p -> table.put(p.getKey(), Constants.TBL_LEFT_V, table.get(p.getKey(), Constants.TBL_LEFT_V) == null ? p.getValue() : new BigDecimal(String.valueOf(table.get(p.getKey(), Constants.TBL_LEFT_V))).add(p.getValue())));
     rightMap.entrySet().forEach(p -> table.put(p.getKey(), Constants.TBL_RIGHT_V, table.get(p.getKey(), Constants.TBL_RIGHT_V) == null ? p.getValue() : new BigDecimal(String.valueOf(table.get(p.getKey(), Constants.TBL_RIGHT_V))).add(p.getValue())));
     }

     }

     /**
     * 初始化查询时间
     *
     * @param context
     */
    public void initCheckTime(CheckContext context) {
        if (context.getBeginTime() == null && (StringUtils.equals(context.getBizTag(), BizTagEnum.FRESH_SORTING_COMPARE.getValue())||
                StringUtils.equals(context.getBizTag(), BizTagEnum.FROZEN_COMPARE.getValue()))) {
            //更新上下文
            context.setBeginTime(DateUtil.todayStart());
            context.setEndTime(DateUtil.addDays(context.getBeginTime(),1));
        }
    }

}
