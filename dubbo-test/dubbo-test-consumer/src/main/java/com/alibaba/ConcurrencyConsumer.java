package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.service.DemoService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyConsumer {
    public static void main(String[] args) throws InterruptedException {

        ApplicationConfig applicationConfig = new ApplicationConfig("concurrency-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(()->{
                ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<>();
                serviceReferenceConfig.setApplication(applicationConfig);
                serviceReferenceConfig.setRegistry(registryConfig);
                serviceReferenceConfig.setInterface(DemoService.class);
                serviceReferenceConfig.setVersion("1.0.0");
                serviceReferenceConfig.setProtocol("dubbo");
                //粘滞连接用于有状态服务，尽可能让客户端总是向同一提供者发起调用，除非该提供者挂了，再连另一台。
                //粘滞连接将自动开启延迟连接，以减少长连接数,支持方法级别的粘滞连接
                serviceReferenceConfig.setSticky(true);
                countDownLatch.countDown();
                DemoService demoService = serviceReferenceConfig.get();
                System.out.println(demoService.echo("consumer" + finalI + " is running"));
            }).start();
        }
        countDownLatch.await();
    }
}
