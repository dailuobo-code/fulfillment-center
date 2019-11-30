package com.mallcai.fulfillment.pe;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mallcai.job.config.EnableMallCaiJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 * @author zhanghao
 * @date 2019年08月13日23:39:24
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan({"com.mallcai.fulfillment.pe.infrastructure.config"})
@ImportResource(value = {"classpath*:spring/application.xml"})
@EnableScheduling
@EnableMallCaiJob
public class Application {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
                 FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
                 FastJsonConfig fastJsonConfig = new FastJsonConfig();
                 fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
                 fastConverter.setFastJsonConfig(fastJsonConfig);
                 HttpMessageConverter<?> converter = fastConverter;
                 return new HttpMessageConverters(converter);
             }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}