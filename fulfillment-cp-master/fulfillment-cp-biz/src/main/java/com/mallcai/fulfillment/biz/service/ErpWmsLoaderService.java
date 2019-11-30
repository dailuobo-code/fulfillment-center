package com.mallcai.fulfillment.biz.service;

import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.loader.BigDecimalLoader;
import com.mallcai.fulfillment.biz.loader.DataIndex;
import com.mallcai.fulfillment.biz.loader.checkdata.ErpLeftLoader;
import com.mallcai.fulfillment.biz.loader.checkdata.FrozenLeftLoader;
import com.mallcai.fulfillment.biz.loader.checkdata.WmsRightLoader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.BizTagEnum;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import com.mallcai.manager.common.constant.Constants;
import com.mallcai.manager.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ErpWmsLoaderService {

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

    /**
     * WMS 和 ERP 构建相同的key的连接字符 key 格式是 warehouseCode + "@" + storeNo + "@" + productNo
     */
    public final static String KEY_JOINER = "@";


    /**
     * 本次冻品处理为临时处理流程，不走正规的处理
     *
     * @param context
     */
    public void loadCheckDataAndProcess(CheckContext context) {
        log.info("即将开始装载对账数据...checkNo={}", context.getCheckNo());

        TaskConfig config = context.getConfig();
        // find dataIndex
        DataIndex index = applicationContext.getBean(config.getLoadIndexBean(), DataIndex.class);
        initCheckTime(context);

        BigDecimalLoader erpLeftLoader = applicationContext.getBean(config.getLeftLoaderBean(), BigDecimalLoader.class);
        BigDecimalLoader wmsRightLoader = applicationContext.getBean(config.getRightLoaderBean(), BigDecimalLoader.class);

        //load left right data
        Map<String, BigDecimal> leftMap = erpLeftLoader.loadResource(context);

        List<String> indies = leftMap.keySet().stream().collect(Collectors.toList());
        context.setIndex(indies);

        // wms 出库单的每一条明细
        Map<String, BigDecimal> rightMap = wmsRightLoader.loadResource(context);
        //context.setTotalCount(rightMap.size());

        //record result
        Table<String, String, Object> table = context.getResult();

        leftMap.entrySet().forEach(p ->
            table.put(p.getKey(), Constants.TBL_LEFT_V,
                table.get(p.getKey(), Constants.TBL_LEFT_V) == null ?
                    p.getValue()
                    : ((BigDecimal) table.get(p.getKey(), Constants.TBL_LEFT_V)).add(p.getValue())
            )
        );

        rightMap.entrySet().forEach(p ->
            table.put(p.getKey(), Constants.TBL_RIGHT_V,
                table.get(p.getKey(), Constants.TBL_RIGHT_V) == null ?
                    p.getValue()
                    : ((BigDecimal) table.get(p.getKey(), Constants.TBL_RIGHT_V)).add(p.getValue())
            )
        );
    }

    /**
     * 初始化查询时间
     *
     * @param context
     */
    public void initCheckTime(CheckContext context) {
        //更新上下文
        context.setBeginTime(DateUtil.todayStart());
        context.setEndTime(DateUtil.addDays(context.getBeginTime(),1));
    }


}
