package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

/**
 * 需要在dubbo2.7版本以上
 */
public class TLSProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("concurrency-provider");

        RegistryConfig registry = new RegistryConfig("nacos://139.196.154.217:8848");

//        SslConfig sslConfig = new SslConfig();
//        sslConfig.setServerKeyCertChainPath("path to cert");
//        sslConfig.setServerPrivateKeyPath(args[1]);
//// 如果开启双向 cert 认证
//        if (mutualTls) {
//            sslConfig.setServerTrustCertCollectionPath(args[2]);
//        }
//
//        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo/grpc");
//        protocolConfig.setSslEnabled(true);
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20881);
        protocol.setThreads(20);

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registry);
        serviceConfig.setProtocol(protocol);
        //服务器端并发执行（或占用线程池线程数）不能超过 10 个
        serviceConfig.setExecutes(10);
        serviceConfig.setConnections(10);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        serviceConfig.setVersion("1.0.0");


        serviceConfig.export();

        LockSupport.park(ConcurrencyProvider.class);
    }
}
