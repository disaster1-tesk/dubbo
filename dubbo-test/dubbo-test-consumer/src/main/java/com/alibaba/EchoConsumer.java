package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.service.DemoService;

import java.util.concurrent.locks.LockSupport;

/**
 * 回声测试用于检测服务是否可用，回声测试按照正常请求流程执行，能够测试整个调用是否通畅，可用于监控。
 * 所有服务自动实现 EchoService 接口，只需将任意服务引用强制转型为 EchoService，即可使用
 */
public class EchoConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("echo-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<DemoService>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setVersion("1.0.0");
        serviceReferenceConfig.setProtocol("dubbo");
        serviceReferenceConfig.setInterface(DemoService.class);
        DemoService demoService = serviceReferenceConfig.get();

        EchoService echoService = (EchoService) demoService; // 强制转型为EchoService

        // 回声测试可用性
        String status = (String) echoService.$echo("OK");
        System.out.println("status = " + status);


        LockSupport.park(EchoConsumer.class);
    }
}
