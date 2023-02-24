package com.alibaba;

import com.alibaba.async.AsyncService;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import java.util.concurrent.CompletableFuture;

public class AsyncConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("cache-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");


        ReferenceConfig<AsyncService> serviceReferenceConfig = new ReferenceConfig<AsyncService>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setInterface(AsyncService.class);
        serviceReferenceConfig.setVersion("1.0.0");
        serviceReferenceConfig.setProtocol("dubbo");


        AsyncService demoService = serviceReferenceConfig.get();

        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = demoService.sayHello("async call request");
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
        // 早于结果输出
        System.out.println("Executed before response return.");
    }
}
