package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

/**
 * redis作为注册中心
 */
public class RedisProvider {
    public static void main(String[] args) {

        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig applicationConfig = new ApplicationConfig("redis-provider");

        RegistryConfig registryConfig = new RegistryConfig("redis://139.196.154.217:6379");
        registryConfig.setUsername("root");
        registryConfig.setPassword("123456");

        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo");
        protocolConfig.setThreadpool("fixed");
        protocolConfig.setThreads(10);

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);

        serviceConfig.export();
        LockSupport.park(RedisProvider.class);
    }
}
