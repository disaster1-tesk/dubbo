package com.alibaba;

import com.alibaba.dubbo.config.*;
import com.alibaba.service.DemoService;
import com.alibaba.validation.ParamValidationService;
import com.alibaba.validation.impl.ParamValidationServiceImpl;
import com.google.common.collect.Lists;

import java.util.concurrent.locks.LockSupport;

public class ValidationProvider {
    public static void main(String[] args) {
        ParamValidationServiceImpl paramValidationService = new ParamValidationServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("validation-provider");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setThreads(20);
        protocol.setThreadpool("eager");

        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("save");
        methodConfig.setRetries(1);
        methodConfig.setTimeout(2000);

        ServiceConfig<ParamValidationService> serviceConfig = new ServiceConfig<ParamValidationService>();
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setRef(paramValidationService);
        serviceConfig.setInterface(ParamValidationService.class);
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setValidation("true");
        serviceConfig.setMethods(Lists.newArrayList(methodConfig));

        serviceConfig.export();

        LockSupport.park(ValidationProvider.class);
    }
}
