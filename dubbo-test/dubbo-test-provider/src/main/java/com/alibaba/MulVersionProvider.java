package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImp1;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

/**
 * 多版本的提供者
 */
public class MulVersionProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        DemoServiceImp1 demoServiceImp1 = new DemoServiceImp1();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("mul-version-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("nacos://139.196.154.217:8848");
        registry.setUsername("disaster");
        registry.setPassword("123456");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setThreads(20);
        protocol.setThreadpool("limited");

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registry);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        serviceConfig.setVersion("1.0.0");


        ServiceConfig<DemoService> serviceConfig1 = new ServiceConfig<DemoService>();
        serviceConfig1.setApplication(application);
        serviceConfig1.setRegistry(registry);
        serviceConfig1.setInterface(DemoService.class);
        serviceConfig1.setRef(demoServiceImp1);
        serviceConfig1.setVersion("1.1.0");


        serviceConfig.export();
        serviceConfig1.export();

        LockSupport.park(MulVersionProvider.class);
    }
}
