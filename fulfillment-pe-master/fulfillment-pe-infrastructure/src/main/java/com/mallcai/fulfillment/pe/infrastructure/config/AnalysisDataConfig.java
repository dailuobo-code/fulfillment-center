package com.mallcai.fulfillment.pe.infrastructure.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AnalysisDataConfig {

    @Value("${fpe.analysis.db.url}")
    private String dbUrl;

    @Value("${fpe.analysis.db.username}")
    private String dbUserName;

    @Value("${fpe.analysis.db.password}")
    private String dbPasswd;

    @Value("${db.driverClassName}")
    private String dbClassName;

    @Value(value = "${db.filters}")
    private String dbFilter;

    //最大并发连接数
    @Value(value = "${db.maxActive}")
    private Integer maxActive;

    //初始化连接数量
    @Value(value = "${db.initialSize}")
    private Integer initialSize;

    //配置获取连接等待超时的时间
    @Value(value = "${db.maxWait}")
    private Long maxWait;

    //最小空闲连接数
    @Value(value = "${db.minIdle}")
    private Integer minIdle;

    //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    @Value(value = "${db.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;

    //配置一个连接在池中最小生存的时间，单位是毫秒
    @Value(value = "${db.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;

    @Value(value = "${db.validationQuery}")
    private String validationQuery;

    @Value(value = "${db.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value(value = "${db.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value(value = "${db.testOnReturn}")
    private Boolean testOnReturn;

    @Value(value = "${db.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;

    //打开removeAbandoned功能
    @Value(value = "${db.removeAbandoned}")
    private Boolean removeAbandoned;

    //1800秒，也就是30分钟
    @Value(value = "${db.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;

    // 关闭abanded连接时输出错误日志
    @Value(value = "${db.logAbandoned}")
    private Boolean logAbandoned;

    @Bean(name = "fdaSource",initMethod = "init",destroyMethod = "close")
    @Primary
    public DruidDataSource getDateSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(dbUserName);
        druidDataSource.setPassword(dbPasswd);
        druidDataSource.setUrl(dbUrl);
        druidDataSource.setDriverClassName(dbClassName);
        druidDataSource.setFilters(dbFilter);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setLogAbandoned(logAbandoned);
        return druidDataSource;
    }

    @Bean(name = "fdaNamedTemplate")
    public NamedParameterJdbcTemplate fdaNamedTemplate(@Qualifier("fdaSource") DataSource datasource) {
        return new NamedParameterJdbcTemplate(datasource);
    }


    @Bean(name = "fpeNamedTemplate")
    public NamedParameterJdbcTemplate fpeNamedTemplate(@Qualifier("fpeDataSource") DataSource datasource) {
        return new NamedParameterJdbcTemplate(datasource);
    }

}
