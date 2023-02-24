package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;
import com.google.common.collect.Lists;

public class PolymerizationConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig("polymerization-consumer");


        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");


        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<DemoService>();
        serviceReferenceConfig.setMerger("list");
        serviceReferenceConfig.setGroup("aaa,bbb");
        serviceReferenceConfig.setInterface(DemoService.class);
        serviceReferenceConfig.setApplication(application);
        serviceReferenceConfig.setRegistry(registryConfig);

        DemoService demoService = serviceReferenceConfig.get();
        System.out.println("demoService = " + demoService.echo());

    }
}
