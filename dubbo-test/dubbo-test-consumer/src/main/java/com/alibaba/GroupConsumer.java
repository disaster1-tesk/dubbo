package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;

import java.util.concurrent.locks.LockSupport;

public class GroupConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("group-consumer");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("nacos://139.196.154.217:8848");
        registry.setUsername("nacos");
        registry.setPassword("nacos");

        ReferenceConfig<DemoService> consumer = new ReferenceConfig<DemoService>();
        consumer.setProtocol("dubbo");
        consumer.setGroup("disaster");
        consumer.setInterface(DemoService.class);
        consumer.setApplication(application);
        consumer.setRegistry(registry);

        DemoService demoService = consumer.get();
        System.out.println(demoService.echo("hello world"));

        LockSupport.park(GroupConsumer.class);
    }
}
