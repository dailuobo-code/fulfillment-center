package com.mallcai.fulfillment.dp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Hello world!
 * @author zhanghao
 * @date 2019-08-15 15:34:25
 */
@SpringBootApplication
@ImportResource(value = {"classpath*:spring/application.xml"})
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
