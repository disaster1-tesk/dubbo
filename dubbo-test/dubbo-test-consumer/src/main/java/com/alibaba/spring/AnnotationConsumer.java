package com.alibaba.spring;


import com.alibaba.config.DubboConfig;
import com.alibaba.service.DemoService;
import com.alibaba.service.DemoServiceTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class AnnotationConsumer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = ApplicationUtil.setApplicationContext(DubboConfig.class);
        applicationContext.start();
        DemoServiceTest bean = applicationContext.getBean(DemoServiceTest.class);
        String hello_world = bean.echo("hello world");
        System.out.println("hello_world = " + hello_world);
        LockSupport.park(AnnotationConsumer.class);
    }
}
