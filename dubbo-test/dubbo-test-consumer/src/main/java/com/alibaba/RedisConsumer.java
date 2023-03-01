package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;

import java.util.concurrent.locks.LockSupport;

public class RedisConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("redis-provider");

        RegistryConfig registryConfig = new RegistryConfig("redis://139.196.154.217:6379");
        registryConfig.setUsername("root");
        registryConfig.setPassword("123456");


        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setInterface(DemoService.class);

        DemoService demoService = serviceReferenceConfig.get();

        System.out.println(demoService.echo("test"));

        LockSupport.park(RedisConsumer.class);
    }
}
