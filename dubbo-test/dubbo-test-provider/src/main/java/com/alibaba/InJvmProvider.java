package com.alibaba;

import com.alibaba.dubbo.config.*;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

public class InJvmProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig applicationConfig = new ApplicationConfig("injvm-provider");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setThreadpool("fixed");
        protocolConfig.setThreads(20);


        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setRef(demoService);
        serviceConfig.setInterface(DemoService.class);

        serviceConfig.export();

        LockSupport.park(InJvmProvider.class);

    }
}
