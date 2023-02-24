package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.concurrent.locks.LockSupport;

/**
 * MessagePack是一种高效的二进制序列化格式。它允许您在多种语言(如JSON)之间交换数据。但它更快更小。短整型被编码成一个字节，而典型的短字符串除了字符串本身只需要一个额外的字节
 */
public class MsgpackProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig applicationConfig = new ApplicationConfig("msgpack-provider");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setId("msgpack");
        protocolConfig.setSerialization("msgpack");

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        serviceConfig.setVersion("1.0.0");

        serviceConfig.export();

        LockSupport.park(MsgpackProvider.class);
    }
}
