/*
package com.mallcai.fulfillment.pe.biz.service.task;


import com.mallcai.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.mallcai.ddframe.job.lite.spring.processor.ScheduledAnnotationProcessor;
import com.mallcai.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.mallcai.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

*/
/**
 * @author wangjingcheng
 *//*

@Slf4j
@Component
@ConditionalOnExpression("${scheduler.enable:true}")
public class SchedulerConfig {

    @Value("${scheduler.dbstorage.enable:false}")
    private boolean enableDbStorage;

    @Value("${scheduler.regCenter.overwrite:false}")
    private boolean shouldOverwriteliteJobConfig;

    @Autowired
    private ApplicationContext applicationContext;


    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${scheduler.regCenter.serverList}") final String serverList,
                                             @Value("${scheduler.regCenter.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

    @Bean
    public ScheduledAnnotationProcessor scheduledAnnotationProcessor(ZookeeperRegistryCenter zookeeperRegistryCenter) {
        String shardingStrategyClass = "com.mallcai.ddframe.job.lite.api.strategy.impl.RotateServerByNameJobShardingStrategy";

        if (enableDbStorage) {
            DataSource dataSource = (DataSource) applicationContext.getBean("getOrzDataSources");
            log.info("init elastic job with db storage");
            return new ScheduledAnnotationProcessor(zookeeperRegistryCenter, new JobEventRdbConfiguration(dataSource), shardingStrategyClass, shouldOverwriteliteJobConfig);
        }

        return new ScheduledAnnotationProcessor(zookeeperRegistryCenter, shardingStrategyClass, shouldOverwriteliteJobConfig);
    }

}
*/
