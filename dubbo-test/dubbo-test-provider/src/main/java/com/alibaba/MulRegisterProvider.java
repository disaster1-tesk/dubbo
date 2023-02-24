package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

/**
 * 多注册中心的案例
 *
 * @author disaster
 * @version 1.0
 */
public class MulRegisterProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();

        ApplicationConfig application = new ApplicationConfig();
        application.setName("mul-register-provider");

        ArrayList<RegistryConfig> registryConfigs = new ArrayList<RegistryConfig>();
        RegistryConfig zk = new RegistryConfig();
        zk.setVersion("1.0.0");
        zk.setUsername("disaster");
        zk.setPassword("123456");
        zk.setAddress("139.196.154.217");
        zk.setId("zk");
        zk.setProtocol("zookeeper");
        zk.setPort(2181);


        RegistryConfig nacos = new RegistryConfig();
        nacos.setVersion("1.0.0");
        nacos.setUsername("disaster");
        nacos.setPassword("123456");
        nacos.setAddress("139.196.154.217");
        nacos.setProtocol("nacos");
        nacos.setPort(8848);
        nacos.setId("nacos");

        registryConfigs.add(zk);
        registryConfigs.add(nacos);

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistries(registryConfigs);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);

        serviceConfig.export();

        LockSupport.park(MulRegisterProvider.class);
    }
}
