package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;

import java.util.concurrent.locks.LockSupport;

public class MsgpackConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("msgpack-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<DemoService>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setInterface(DemoService.class);
        serviceReferenceConfig.setVersion("1.0.0");

        DemoService demoService = serviceReferenceConfig.get();
        String disaster = demoService.setUser(User.builder().name("disaster").age(20).build());
        System.out.println("disaster = " + disaster);


        LockSupport.park(MsgpackConsumer.class);
    }
}
