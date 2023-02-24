package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImp1;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

public class PolymerizationProvider {
    public static void main(String[] args) {
        DemoServiceImp1 demoServiceImp1 = new DemoServiceImp1();
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("polymerization-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("nacos://139.196.154.217:8848");


        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setThreadpool("limited");
        protocol.setName("dubbo");

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registry);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setGroup("group1");
        serviceConfig.setRef(demoService);

        ServiceConfig<DemoService> serviceConfig1 = new ServiceConfig<DemoService>();
        serviceConfig1.setApplication(application);
        serviceConfig1.setRegistry(registry);
        serviceConfig1.setProtocol(protocol);
        serviceConfig1.setInterface(DemoService.class);
        serviceConfig1.setGroup("group2");
        serviceConfig1.setRef(demoServiceImp1);

        serviceConfig.export();
        serviceConfig1.export();

        LockSupport.park(PolymerizationProvider.class);

    }
}
