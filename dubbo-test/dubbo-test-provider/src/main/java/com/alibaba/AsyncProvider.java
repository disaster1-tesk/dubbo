package com.alibaba;

import com.alibaba.async.AsyncService;
import com.alibaba.async.IAsyncServiceImpl;
import com.alibaba.dubbo.config.*;
import com.google.common.collect.Lists;

import java.util.concurrent.locks.LockSupport;

/**
 * 异步调用只支持dubbo协议
 */
public class AsyncProvider {
    public static void main(String[] args) {
        IAsyncServiceImpl iAsyncService = new IAsyncServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("async-provider");

        RegistryConfig registry = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(20);

        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHello");
        methodConfig.setSent(true);
        methodConfig.setReturn(false);

        ServiceConfig<AsyncService> service = new ServiceConfig<>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setInterface(AsyncService.class);
        service.setRef(iAsyncService);
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setVersion("1.0.0");
//        service.setMethods(Lists.newArrayList(methodConfig));
        service.setAsync(true);

        //暴露及注册服务
        service.export();

        LockSupport.park(Provider.class);
    }
}
