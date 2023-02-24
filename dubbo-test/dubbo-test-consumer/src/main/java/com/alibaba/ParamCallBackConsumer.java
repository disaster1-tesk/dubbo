package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.paramcallback.CallbackService;

import java.util.concurrent.locks.LockSupport;

/**
 * 参数回调方式与调用本地 callback 或 listener 相同，只需要在 Spring 的配置文件中声明哪个参数是 callback 类型即可。
 * Dubbo 将基于长连接生成反向代理，这样就可以从服务器端调用客户端逻辑。
 */
public class ParamCallBackConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("param-callback-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");


        ConsumerConfig consumerConfig = new ConsumerConfig();


        ReferenceConfig<CallbackService> consumer = new ReferenceConfig<>();
        consumer.setProtocol("dubbo");
        consumer.setInterface(CallbackService.class);
        consumer.setApplication(application);
        consumer.setRegistry(registryConfig);
        consumer.setConsumer(consumerConfig);

        CallbackService callbackService = consumer.get();
        callbackService.addListener("foo.bar", msg -> System.out.println("callback1:" + msg));

        LockSupport.park(ParamCallBackConsumer.class);
    }
}
