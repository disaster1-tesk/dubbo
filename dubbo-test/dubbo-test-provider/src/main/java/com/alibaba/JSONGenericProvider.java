package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.generic.IGenericService;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

public class JSONGenericProvider {

    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig applicationConfig = new ApplicationConfig("json-generic-provider");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo");
        protocolConfig.setThreads(20);
        protocolConfig.setThreadpool("fixed");
        protocolConfig.setKeepAlive(true);

        genericProvider(demoService, applicationConfig, registryConfig, protocolConfig);
//        genericProvider1(applicationConfig, registryConfig, protocolConfig);
    }

    private static void genericProvider(DemoServiceImpl demoService, ApplicationConfig applicationConfig, RegistryConfig registryConfig, ProtocolConfig protocolConfig) {
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setRef(demoService);
        serviceConfig.setTimeout(2000);

        serviceConfig.setVersion("1.0.0");

        serviceConfig.export();

        LockSupport.park(JSONGenericProvider.class);
    }

    //泛化实现
    private static void genericProvider1(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ProtocolConfig protocolConfig) {
        IGenericService iGenericService = new IGenericService();
        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<GenericService>();
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setRef(iGenericService);
        serviceConfig.setTimeout(2000);

        serviceConfig.setVersion("1.0.0");

        serviceConfig.export();

        LockSupport.park(JSONGenericProvider.class);
    }
}
