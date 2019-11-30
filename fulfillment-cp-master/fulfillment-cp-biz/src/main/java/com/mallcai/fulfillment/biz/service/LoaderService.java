package com.mallcai.fulfillment.biz.service;

import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.loader.DataIndex;
import com.mallcai.fulfillment.biz.loader.Loader;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LoaderService {

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
            Loader leftLoader = applicationContext.getBean(config.getLeftLoaderBean(), Loader.class);
            Loader rightLoader = applicationContext.getBean(config.getRightLoaderBean(), Loader.class);

            Map<String, Integer> leftMap = leftLoader.loadResource(context);
            Map<String, Integer> rightMap = rightLoader.loadResource(context);

            //record result
            Table<String, String, Object> table = context.getResult();
            leftMap.entrySet().forEach(p -> table.put(p.getKey(), Constants.TBL_LEFT_V, table.get(p.getKey(), Constants.TBL_LEFT_V) == null ? p.getValue() : (int) table.get(p.getKey(), Constants.TBL_LEFT_V) + p.getValue()));
            rightMap.entrySet().forEach(p -> table.put(p.getKey(), Constants.TBL_RIGHT_V, table.get(p.getKey(), Constants.TBL_RIGHT_V) == null ? p.getValue() : (int) table.get(p.getKey(), Constants.TBL_RIGHT_V) + p.getValue()));
        }

    }

    /**
     * 初始化查询时间
     *
     * @param context
     */
    public void initCheckTime(CheckContext context) {
        Date endTime;
        Date beginTime;
        if (context.getBeginTime() == null && StringUtils.equals(context.getBizTag(), BizTagEnum.TRADE_COMPARE.getValue())) {
            String redisBeginTime = redisService.get(Constants.REDIS_TRADE_SUCCESS_TIME);
            //默认取5分钟之前的，给数据的一致性留下时间
            endTime = DateUtil.addMinutes(new Date(), -5);
            if (StringUtils.isEmpty(redisBeginTime)) {
                //如果没有，则自行初始化时间
                beginTime = DateUtil.addSeconds(endTime, -(int) context.getBizParam().get(PARAM_INTERVAL));
            } else {
                //如果redis有缓存上次对比成功的时间，则以上次对比成功时间为起始时间，结束时间为当前时间，查询时间范围  左闭右开
                beginTime = DateUtil.parseLDateTime(redisBeginTime);
            }
            //更新上下文
            context.setBeginTime(beginTime);
            context.setEndTime(endTime);
        } else if (context.getBeginTime() == null && StringUtils.equals(context.getBizTag(), BizTagEnum.FROZEN_COMPARE.getValue())) {
            //更新上下文
            context.setBeginTime(DateUtil.todayStart());
            context.setEndTime(new Date());
        }
    }

}
