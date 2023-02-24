package com.alibaba;

import com.alibaba.dubbo.config.*;
import com.alibaba.paramcallback.CallbackService;
import com.alibaba.paramcallback.CallbackServiceImpl;
import com.google.common.collect.Lists;

import java.util.concurrent.locks.LockSupport;

public class ParamCallBackProvider {
    public static void main(String[] args) {
        CallbackServiceImpl callbackService = new CallbackServiceImpl();
        ApplicationConfig applicationConfig = new ApplicationConfig("param-callback-provider");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setThreadpool("fixed");
        protocolConfig.setThreads(20);

        MethodConfig methodConfig = new MethodConfig();
        ArgumentConfig argumentConfig = new ArgumentConfig();
        argumentConfig.setIndex(1);
        argumentConfig.setCallback(true);
        methodConfig.setName("addListener");
        methodConfig.setArguments(Lists.newArrayList(argumentConfig));

        ServiceConfig<CallbackService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setRef(callbackService);
        serviceConfig.setInterface(CallbackService.class);
        serviceConfig.setMethods(Lists.newArrayList(methodConfig));


        serviceConfig.export();

        LockSupport.park(ParamCallBackProvider.class);

    }
}
