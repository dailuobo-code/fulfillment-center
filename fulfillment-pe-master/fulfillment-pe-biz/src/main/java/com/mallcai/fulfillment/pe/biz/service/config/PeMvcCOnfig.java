package com.mallcai.fulfillment.pe.biz.service.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class PeMvcCOnfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

                 super.configureMessageConverters(converters);
                 FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

                 FastJsonConfig fastJsonConfig = new FastJsonConfig();
                 fastJsonConfig.setSerializerFeatures(
                                 SerializerFeature.PrettyFormat
                         );

                 fastConverter.setFastJsonConfig(fastJsonConfig);
                 converters.add(fastConverter);

             }
}
