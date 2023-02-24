package com.alibaba;

import com.alibaba.dubbo.config.*;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;
import com.google.common.collect.Lists;

import java.util.concurrent.locks.LockSupport;

/**
 * 本地伪装 1 通常用于服务降级，比如某验权服务，当服务提供方全部挂掉后，客户端不抛出异常，而是通过 Mock 数据返回授权失败。
 */
public class MockConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("stub-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        //在方法级别配置 Mock
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("setUser");
        methodConfig.setMock(true);
        methodConfig.setMock("force:return fake");

        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setInterface(DemoService.class);
        serviceReferenceConfig.setVersion("1.0.0");
//        serviceReferenceConfig.setMethods(Lists.newArrayList(methodConfig));
        //下面两个设置任何一个都可以
//        serviceReferenceConfig.setMock(true);
//        serviceReferenceConfig.setMock("com.alibaba.service.DemoServiceMock");
        //强制返回指定值
//        serviceReferenceConfig.setMock("force:return fake");
        //强制抛出指定异常
//        serviceReferenceConfig.setMock("force:throw com.foo.MockException");


        DemoService demoService = serviceReferenceConfig.get();
        System.out.println(demoService.setUser(User.builder().name("disaster").age(20).build()));

        LockSupport.park(StubConsumer.class);
    }
}
