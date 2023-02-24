package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

public class ImplicitParamProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig applicationConfig = new ApplicationConfig("json-generic-provider");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo");
        protocolConfig.setThreads(20);
        protocolConfig.setThreadpool("fixed");
        protocolConfig.setKeepAlive(true);



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
}
