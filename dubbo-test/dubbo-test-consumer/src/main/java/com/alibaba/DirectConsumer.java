package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.service.DemoService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;


public class DirectConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-consumer");

        List<MethodConfig> methods = new ArrayList<MethodConfig>();
        MethodConfig method = new MethodConfig();
        method.setName("echo");
        method.setTimeout(2000);
        method.setRetries(0);
        methods.add(method);


        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
        reference.setApplication(application);
        reference.setMethods(methods);
        reference.setUrl("dubbo://127.0.0.1:20880");
        reference.setInterface(DemoService.class);
        reference.setVersion("1.0.0");
        reference.setCheck(true);//可以通过 check="false" 关闭检查，比如，测试时，有些服务不关心，或者出现了循环依赖，必须有一方先启动


        // 和本地bean一样使用xxxService
        DemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

        String echo = demoService.echo("test");
        System.out.println("Receive result ======> " + echo);
        LockSupport.park(Consumer.class);
    }
}
