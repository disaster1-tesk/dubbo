package com.alibaba;


import com.alibaba.dubbo.config.*;
import com.alibaba.service.DemoService;
import com.alibaba.service.impl.DemoServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class Provider {
    public static void main(String[] args) {
        //服务实现
        DemoServiceImpl demoService = new DemoServiceImpl();
        //当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-provider");



        //连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://139.196.154.217:2181");
        registry.setUsername("disaster");
        registry.setPassword("123456");


        //服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(20);
        //不同的派发策略和不同的线程池配置的组合来应对不同的场景
        /**
         * all 所有消息都派发到线程池，包括请求，响应，连接事件，断开事件，心跳等。
         * direct 所有消息都不派发到线程池，全部在 IO 线程上直接执行。
         * message 只有请求响应消息派发到线程池，其它连接断开事件，心跳等消息，直接在 IO 线程上执行。
         * execution 只有请求消息派发到线程池，不含响应，响应和其它连接断开事件，心跳等消息，直接在 IO 线程上执行。
         * connection 在 IO 线程上，将连接断开事件放入队列，有序逐个执行，其它消息派发到线程池。
         */
        protocol.setDispatcher("all");
        /**
         * fixed 固定大小线程池，启动时建立线程，不关闭，一直持有。(缺省)
         * cached 缓存线程池，空闲一分钟自动删除，需要时重建。
         * limited 可伸缩线程池，但池中的线程数只会增长不会收缩。只增长不收缩的目的是为了避免收缩时突然来了大流量引起的性能问题。
         * eager 优先创建Worker线程池。在任务数量大于corePoolSize但是小于maximumPoolSize时，优先创建Worker来处理任务。当任务数量大于maximumPoolSize时，将任务放入阻塞队列中。阻塞队列充满时抛出RejectedExecutionException。(相比于cached:cached在任务数量超过maximumPoolSize时直接抛出异常而不是将任务放入阻塞队列)
         */
        protocol.setThreadpool("fixed");


        //服务提供者配置，如果ServiceConfig中没有配置相应的参数，则默认采用ProviderConfig中的
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setApplication(application);
        providerConfig.setProtocol(protocol);
        providerConfig.setRegistry(registry);
        providerConfig.setVersion("1.1.0");
        providerConfig.setCluster("failover");
        providerConfig.setLoadbalance("leastactive");


        //注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        //服务提供者暴露服务配置
        List<MethodConfig> methods = new ArrayList<MethodConfig>();
        MethodConfig method = new MethodConfig();
        method.setName("echo");
        method.setTimeout(2000);
        method.setRetries(0);
        methods.add(method);


        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
//        service.setApplication(application);
//        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
//        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setProvider(providerConfig);
//        service.setVersion("1.0.0");
        //集群调用失败时，Dubbo 提供了多种容错方案，缺省为 failover 重试，此方法内部会校验是否有相应的cluster去处理容错，如果没有则抛出异常
        service.setCluster("failover");
        //同上面容错一致逻辑
//        service.setLoadbalance("leastactive");
        //暴露及注册服务
        service.export();
        LockSupport.park(Provider.class);
    }
}
