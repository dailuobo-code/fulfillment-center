package com.mallcai.fulfillment.biz.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${dubbo.global.registry.address}")
    private String globalAddress;

    @Value("${dubbo.fulfillment.registry.address}")
    private String cityAddress;

    //读取wms 的配置中心,获取 zookeeper 的配置
    @Value("${dubbo.wms.address}")
    private String wmsAddress;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
        applicationConfig.setQosEnable(true);
        applicationConfig.setQosPort(22225);
        applicationConfig.setQosAcceptForeignIp(false);
        return applicationConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(23000);
        protocolConfig.setName("dubbo");
        return protocolConfig;
    }

    @Bean(name = "global")
    public RegistryConfig globalConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(globalAddress);
        registryConfig.setProtocol("zookeeper");
        registryConfig.setWait(15000);
        registryConfig.setDefault(true);
        return registryConfig;
    }

    @Bean(name = "wms")
    public RegistryConfig wmsConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(wmsAddress);
        registryConfig.setProtocol("zookeeper");
        registryConfig.setWait(15000);
        registryConfig.setDefault(true);
        return registryConfig;
    }

    @Bean(name = "city")
    public RegistryConfig cityConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(globalAddress);
        registryConfig.setProtocol("zookeeper");
        registryConfig.setWait(15000);
        registryConfig.setDefault(true);
        return registryConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(3000);
        return consumerConfig;
    }

}
