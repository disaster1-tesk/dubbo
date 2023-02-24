package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;

import java.util.concurrent.locks.LockSupport;

public class MulVersionConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("group-consumer");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("nacos://139.196.154.217:8848");
        registry.setUsername("nacos");
        registry.setPassword("nacos");

        ReferenceConfig<DemoService> consumer = new ReferenceConfig<DemoService>();
        consumer.setProtocol("dubbo");
        consumer.setInterface(DemoService.class);
        consumer.setApplication(application);
        consumer.setRegistry(registry);
        consumer.setVersion("1.0.0");

        ReferenceConfig<DemoService> consumer1 = new ReferenceConfig<DemoService>();
        consumer1.setProtocol("dubbo");
        consumer1.setInterface(DemoService.class);
        consumer1.setApplication(application);
        consumer1.setRegistry(registry);
        consumer1.setVersion("1.1.0");

        DemoService demoService = consumer.get();
        DemoService demoService1 = consumer1.get();

        System.out.println("demoService = " + demoService.echo());
        System.out.println("demoService1 = " + demoService1.echo());

        LockSupport.park(GroupConsumer.class);
    }
}
