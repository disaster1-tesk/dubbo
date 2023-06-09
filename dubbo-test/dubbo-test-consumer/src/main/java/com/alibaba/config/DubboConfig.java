package com.alibaba.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubbo
@ComponentScan("com.alibaba")
@DubboComponentScan("com.alibaba")
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig(){
        return new ApplicationConfig("annotation-provider");
    }

    @Bean
    public RegistryConfig registryConfig(){
        return new RegistryConfig("nacos://139.196.154.217:8848");
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        return protocol;
    }
}
