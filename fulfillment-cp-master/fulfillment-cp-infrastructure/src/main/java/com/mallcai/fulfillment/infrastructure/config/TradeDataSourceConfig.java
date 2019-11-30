package com.mallcai.fulfillment.infrastructure.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.mallcai.fulfillment.infrastructure.mapper.trade"},sqlSessionFactoryRef="mergeSqlSessionFactory")
public class TradeDataSourceConfig {

    @Value("${merge.db.url}")
    private String mergeDbUrl;

    @Value("${merge.db.username}")
    private String mergeDbUserName;

    @Value("${merge.db.password}")
    private String mergeDbPasswd;

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


    @Bean(name = "mergeDataSource",initMethod = "init",destroyMethod = "close")
    public DruidDataSource getMergeDataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(mergeDbUserName);
        druidDataSource.setPassword(mergeDbPasswd);
        druidDataSource.setUrl(mergeDbUrl);
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

    @Bean(name = "mergeSqlSessionFactory")
    public SqlSessionFactory mergeSqlSessionFactory(@Qualifier("mergeDataSource") DataSource datasource)
        throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        bean.setDataSource(datasource);
        bean.setMapperLocations(pathResolver.getResources("classpath*:mybatis/mapper/trade/*.xml"));
//        bean.setConfigLocation( pathResolver.getResource("classpath:mybatis/config/merge-config.xml"));
        return bean.getObject();
    }
    @Bean("mergeSqlSessionTemplate")
    public SqlSessionTemplate mergeSqlSessionTemplate(
        @Qualifier("mergeSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}
