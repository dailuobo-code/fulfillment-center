package com.mallcai.fulfillment.infrastructure.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {



    @Bean(name="log-filter")
    public Slf4jLogFilter getLogFilter(){

        Slf4jLogFilter logFilter=new Slf4jLogFilter();
        logFilter.setResultSetLogEnabled(false);
        logFilter.setDataSourceLogEnabled(false);
        logFilter.setConnectionLogEnabled(false);
        logFilter.setStatementExecutableSqlLogEnable(true);
        return logFilter;
    }

}
