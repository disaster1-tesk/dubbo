package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;

import java.util.concurrent.locks.LockSupport;

/**
 * 通过令牌验证在注册中心控制权限，以决定要不要下发令牌给消费者，可以防止消费者绕过注册中心访问提供者，
 * 另外通过注册中心可灵活改变授权方式，而不需修改或升级提供者
 */
public class SafeConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("safe-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setInterface(DemoService.class);
        serviceReferenceConfig.setVersion("1.0.0");
        //下面两个设置任何一个都可以
        serviceReferenceConfig.setStub(true);
        serviceReferenceConfig.setListener("consumer");
        serviceReferenceConfig.setCluster("provider");
//        serviceReferenceConfig.setStub("com.alibaba.service.DemoServiceStub");

        DemoService demoService = serviceReferenceConfig.get();
        System.out.println(demoService.setUser(User.builder().name("disaster").age(20).build()));

        LockSupport.park(StubConsumer.class);
    }
}
