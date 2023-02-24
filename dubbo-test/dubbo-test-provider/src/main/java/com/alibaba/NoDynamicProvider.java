package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

/**
 * 有时候希望人工管理服务提供者的上线和下线，此时需将注册中心标识为非动态管理模式。
 */
public class NoDynamicProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("no-dynamic-provider");


        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");
        registryConfig.setUsername("disaster");
        registryConfig.setPassword("disaster");
        //取消动态管理
        registryConfig.setDynamic(false);

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(20);


        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setRef(demoService);
        serviceConfig.setInterface(DemoService.class);

        serviceConfig.export();

        LockSupport.park(NoDynamicProvider.class);
    }
}
