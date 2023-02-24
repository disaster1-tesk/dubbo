package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;

/**
 * 在调用之前、调用之后、出现异常时，会触发 oninvoke、onreturn、onthrow 三个事件，
 * 可以配置当事件发生时，通知哪个类的哪个方法。目前看来api的方式无法进行相应的处理，可以通过spring的配置来做
 */
public class EventConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("echo-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setAsync(true);

        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<DemoService>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setVersion("1.0.0");
        serviceReferenceConfig.setGroup("disaster");
        serviceReferenceConfig.setProtocol("dubbo");
        serviceReferenceConfig.setInterface(DemoService.class);

        DemoService demoService = serviceReferenceConfig.get();
    }
}
