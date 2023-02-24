package com.alibaba;

import com.alibaba.dubbo.config.*;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

public class DelayProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("delay-provider");

        RegistryConfig registry = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20881);
        protocol.setThreads(20);


        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registry);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        //延迟暴露
        serviceConfig.setDelay(5000);

        serviceConfig.export();

        LockSupport.park(DelayProvider.class);
    }
}
