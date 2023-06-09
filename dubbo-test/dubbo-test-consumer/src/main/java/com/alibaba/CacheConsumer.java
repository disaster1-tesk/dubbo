package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;

import java.util.concurrent.locks.LockSupport;

/**
 * lru 基于最近最少使用原则删除多余缓存，保持最热的数据被缓存。
 * threadlocal 当前线程缓存，比如一个页面渲染，用到很多 portal，每个 portal 都要去查用户信息，通过线程缓存，可以减少这种多余访问。
 * jcache 与 JSR107 集成，可以桥接各种缓存实现。
 */
public class CacheConsumer {
    public static void main(String[] args) {
        ReferenceConfigCache referenceCache = ReferenceConfigCache.getCache();
        ApplicationConfig applicationConfig = new ApplicationConfig("cache-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ReferenceConfig<DemoService> serviceReferenceConfig = new ReferenceConfig<DemoService>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setInterface(DemoService.class);
//        serviceReferenceConfig.setVersion("1.0.0");
        serviceReferenceConfig.setCache("lru");
        serviceReferenceConfig.setProtocol("dubbo");

        DemoService demoService = referenceCache.get(serviceReferenceConfig);
//        DemoService demoService = serviceReferenceConfig.get();
        String disaster = demoService.setUser(User.builder().name("disaster").age(20).build());
        System.out.println("disaster = " + disaster);
        String disaster1 = demoService.setUser(User.builder().name("disaster").age(20).build());
        System.out.println("disaster1 = " + disaster1);

        LockSupport.park(MsgpackConsumer.class);
    }
}
