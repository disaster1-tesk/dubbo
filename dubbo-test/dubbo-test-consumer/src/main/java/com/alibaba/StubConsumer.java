package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;

import java.util.concurrent.locks.LockSupport;

/**
 * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑，
 * 比如：做 ThreadLocal 缓存，提前验证参数，调用失败后伪造容错数据等等，此时就需要在 API 中带上 Stub，客户端生成 Proxy 实例，
 * 会把 Proxy 通过构造函数传给 Stub 1，然后把 Stub 暴露给用户，Stub 可以决定要不要去调 Proxy。
 */
public class StubConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("stub-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setInterface(DemoService.class);
        serviceReferenceConfig.setVersion("1.0.0");
        //下面两个设置任何一个都可以
        serviceReferenceConfig.setStub(true);
        serviceReferenceConfig.setStub("com.alibaba.service.DemoServiceStub");

        DemoService demoService = serviceReferenceConfig.get();
        System.out.println(demoService.setUser(User.builder().name("disaster").age(20).build()));

        LockSupport.park(StubConsumer.class);
    }
}
