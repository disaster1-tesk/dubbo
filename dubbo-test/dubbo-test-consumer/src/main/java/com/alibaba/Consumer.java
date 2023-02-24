package com.alibaba;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.service.DemoService;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class Consumer {
    public static void main(String[] args) {
        //当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-consumer");



        //连接注册中心配置
//        RegistryConfig registry = new RegistryConfig();
//        registry.setAddress("zookeeper://139.196.154.217:2181");
//        registry.setUsername("disaster");
//        registry.setPassword("123456");

        RegistryConfig registry = new RegistryConfig();
        registry.setVersion("1.0.0");
        registry.setUsername("disaster");
        registry.setPassword("123456");
        registry.setAddress("139.196.154.217");
        registry.setProtocol("nacos");
        registry.setPort(8848);
        registry.setId("nacos");


        //消费者配置项，如果ReferenceConfig中没有配置相应的参数，则默认采用ConsumerConfig中的
        ConsumerConfig consumerConfig = new ConsumerConfig();
//        consumerConfig.setApplication(application);
//        consumerConfig.setCheck(false);
//        consumerConfig.setRegistry(registry);
//        consumerConfig.setVersion("1.1.0");
        consumerConfig.setGroup("disaster");


        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        List<MethodConfig> methods = new ArrayList<MethodConfig>();
        MethodConfig method = new MethodConfig();
        method.setName("echo");
        method.setTimeout(2000);
        method.setRetries(0);
        methods.add(method);



        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setConsumer(consumerConfig);
        reference.setGroup("disaster");
        reference.setMethods(methods);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(DemoService.class);
//        reference.setVersion("1.0.0");
//        reference.setId("demoService");
        //可以通过 check="false" 关闭检查，比如，测试时，有些服务不关心，或者出现了循环依赖，必须有一方先启动
//        reference.setCheck(true);


        // 和本地bean一样使用xxxService
        DemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        // dubbo服务调用前，通过RpcContext动态设置本次调用的重试次数
//        RpcContext rpcContext = RpcContext.getContext();
//        rpcContext.setAttachment("retries", "5");
//        rpcContext.asyncCall(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("test running");
//            }
//        });


        String echo = demoService.echo("test");
        System.out.println("Receive result ======> " + echo);
        LockSupport.park(Consumer.class);
    }
}
