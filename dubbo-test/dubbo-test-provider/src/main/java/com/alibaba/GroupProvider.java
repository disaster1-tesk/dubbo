package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;


/**
 *
 */
public class GroupProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("group-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setVersion("1.0.0");
        registry.setUsername("disaster");
        registry.setPassword("123456");
        registry.setAddress("139.196.154.217");
        registry.setProtocol("nacos");
        registry.setPort(8848);
        registry.setId("nacos");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(20);

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setGroup("disaster");
        serviceConfig.setRegistry(registry);
        serviceConfig.setApplication(application);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        serviceConfig.setId("demoService");


        serviceConfig.export();

        LockSupport.park(GroupProvider.class);

    }
}
