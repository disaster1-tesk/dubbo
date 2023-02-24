package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;

/**
 * dubbo 消费者如果是在一台机器上进行远程调用，默认会走本地调用
 */
public class InJvmConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("injvm-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");


        ConsumerConfig consumerConfig = new ConsumerConfig();


        ReferenceConfig<DemoService> consumer = new ReferenceConfig<>();
        consumer.setProtocol("dubbo");
        consumer.setInterface(DemoService.class);
        consumer.setApplication(application);
        consumer.setRegistry(registryConfig);
        consumer.setConsumer(consumerConfig);

        DemoService demoService = consumer.get();
        System.out.println(demoService.echo());


    }
}
