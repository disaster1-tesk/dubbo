package com.alibaba;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;

public class JSONGenericConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("json-generic-consumer");

        RegistryConfig registryConfig = new RegistryConfig("nacos://139.196.154.217:8848");

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<GenericService>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setValidation("false");
        referenceConfig.setProtocol("dubbo");
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");
        RpcContext.getContext().setAttachment("generic", "gson");

        // 声明为泛化接口
        referenceConfig.setGeneric(true);
        referenceConfig.setCheck(false);
        GenericService genericService = referenceConfig.get();
        //此方式是从缓存中拿数据
//        GenericService genericService = ReferenceConfigCache.getCache().get(referenceConfig);

        // 传递参数对象的json字符串进行一次调用
        Object res = genericService.$invoke("setUser", new String[]{"com.alibaba.service.User"}, new Object[]{User.builder().name("disaster").age(20).build()});
        System.out.println("result[setUser]：" + res); // 响应结果:result[setUser]：{name=Tom, class=com.xxx.api.service.User, age=24}

    }
}
