package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 多种协议的提供者,多种协议注册的服务会在注册中心生成三个实例
 */
public class MulProtocolProvider {
    public static void main(String[] args) throws InterruptedException {
        DemoServiceImpl demoService = new DemoServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("mul-protocol-provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("nacos://139.196.154.217:8848");
        registry.setUsername("disaster");
        registry.setPassword("123456");

        ArrayList<ProtocolConfig> protocolConfigs = new ArrayList<ProtocolConfig>();
        ProtocolConfig dubbo = new ProtocolConfig();
        dubbo.setName("dubbo");
        dubbo.setPort(20880);
        dubbo.setThreads(20);

        ProtocolConfig rmi = new ProtocolConfig();
        rmi.setName("rmi");
        rmi.setPort(20881);
        rmi.setThreads(20);

        ProtocolConfig hessian = new ProtocolConfig();
        hessian.setName("hessian");
        hessian.setPort(20882);
        hessian.setThreads(20);
        protocolConfigs.add(dubbo);
        protocolConfigs.add(rmi);
        protocolConfigs.add(hessian);



        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setCluster("failover");
        serviceConfig.setLoadbalance("leastactive");
        serviceConfig.setProtocols(protocolConfigs);
        serviceConfig.setApplication(application);
        serviceConfig.setRef(demoService);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRegistry(registry);


        serviceConfig.export();
        new CountDownLatch(1).await();

    }
}
