package com.alibaba;

import com.alibaba.dubbo.config.*;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 直连的方式需要配置此jvm参数： -Dcom.alibaba.service.DemoService=dubbo://localhost:20880
 *
 */
public class DirectProvider {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("direct-connection-provider");

        //不采用注册中心
        RegistryConfig registry = new RegistryConfig();
        registry.setRegister(false);



        List<MethodConfig> methods = new ArrayList<MethodConfig>();
        MethodConfig method = new MethodConfig();
        method.setName("echo");
        method.setTimeout(2000);
        method.setRetries(0);
        methods.add(method);

        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
        service.setApplication(application);
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setVersion("1.0.0");
        service.setRegistry(registry);//没有注册中心的注册服务


        //集群调用失败时，Dubbo 提供了多种容错方案，缺省为 failover 重试，此方法内部会校验是否有相应的cluster去处理容错，如果没有则抛出异常
        service.setCluster("failover");
        //同上面容错一致逻辑
        service.setLoadbalance("leastactive");
        //暴露及注册服务
        service.export();
        LockSupport.park(Provider.class);
    }
}
