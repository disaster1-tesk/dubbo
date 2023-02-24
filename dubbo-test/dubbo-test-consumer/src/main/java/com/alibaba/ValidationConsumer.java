package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.validation.ParamValidationService;
import com.alibaba.validation.ValidationParameter;

import java.util.Date;
import java.util.concurrent.locks.LockSupport;

public class ValidationConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("validation-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ReferenceConfig<ParamValidationService> serviceReferenceConfig = new ReferenceConfig<ParamValidationService>();
        serviceReferenceConfig.setProtocol("dubbo");
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setInterface(ParamValidationService.class);
        serviceReferenceConfig.setVersion("1.0.0");
        serviceReferenceConfig.setValidation("true");


        ParamValidationService paramValidationService = serviceReferenceConfig.get();
        paramValidationService.save(ValidationParameter.builder()
                .age(-1)
                .email("2272317764@qq.com")
                .loginDate(new Date())
                .name("disaster")
                .build());


        LockSupport.park(ValidationConsumer.class);

    }
}
