package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

/**
 * 只进行订阅不注册的提供者
 */
public class JustSubscribeProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("Just-Subscribe-Provider");

        RegistryConfig registry = new RegistryConfig();
        registry.setRegister(false);

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setRegister(false);
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(20);

        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()

        //暴露及注册服务
        service.export();
        LockSupport.park(Provider.class);
    }
}
