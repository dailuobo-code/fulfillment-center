package com.mallcai.fulfillment.deploy;

import com.mallcai.job.config.EnableMallCaiJob;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@EnableDubbo(scanBasePackages = "com.mallcai.fulfillment.biz.service")
@SpringBootApplication
@ComponentScan({"com.mallcai.fulfillment.infrastructure","com.mallcai.fulfillment.biz","com.mallcai.fulfillment.deploy","com.mallcai.manager.common"})
@EnableMallCaiJob
public class Application {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}