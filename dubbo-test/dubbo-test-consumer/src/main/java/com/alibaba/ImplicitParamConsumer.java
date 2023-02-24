package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.service.DemoService;

import java.util.concurrent.locks.LockSupport;

/**
 * 可以通过 RpcContext 上的 setAttachment 和 getAttachment 在服务消费方和提供方之间进行参数的隐式传递。
 */
public class ImplicitParamConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("implicit-param-consumer");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("nacos://139.196.154.217:8848");

        ReferenceConfig<DemoService> consumer = new ReferenceConfig<DemoService>();
        consumer.setProtocol("dubbo");
        consumer.setInterface(DemoService.class);
        consumer.setApplication(application);
        consumer.setRegistry(registry);
        consumer.setVersion("1.0.0");

        DemoService demoService = consumer.get();
        RpcContext.getContext().setAttachment("index", "1"); // 隐式传参，后面的远程调用都会隐式将这些参数发送到服务器端，类似cookie，用于框架集成，不建议常规业务使用
        System.out.println(demoService.echo("hello world"));
    }
}
