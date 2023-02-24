package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛化接口调用方式主要用于客户端没有 API 接口及模型类元的情况，参数及返回值中的所有 POJO 均用 Map 表示，通常用于框架集成，比如：实现一个通用的服务测试框架，可通过 GenericService 调用所有服务实现。
 */
public class GenericConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("generic-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        genericConsumer(applicationConfig, registryConfig);
    }

    private static void genericConsumer(ApplicationConfig applicationConfig, RegistryConfig registryConfig) {

        ReferenceConfig<GenericService> serviceReferenceConfig = new ReferenceConfig<GenericService>();
        serviceReferenceConfig.setApplication(applicationConfig);
        serviceReferenceConfig.setRegistry(registryConfig);
        serviceReferenceConfig.setGeneric(true);
        serviceReferenceConfig.setVersion("1.0.0");
        // 弱类型接口名
        serviceReferenceConfig.setInterface("com.alibaba.service.DemoService");

        // 用org.apache.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = serviceReferenceConfig.get();

        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke("echo", new String[]{"java.lang.String"}, new Object[]{"disaster will fit"});
        System.out.println("result = " + result);

        // 用Map表示POJO参数，如果返回值为POJO也将自动转成Map
        Map<String, Object> person = new HashMap<String, Object>();
        person.put("name", "xxx");
        person.put("age", 20);

        // 如果返回POJO将自动转成Map
        Object result1 = genericService.$invoke("setUser", new String[]
                {"com.alibaba.service.User"}, new Object[]{person});
        System.out.println("result1 = " + result1);
    }

}
