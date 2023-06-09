package com.alibaba.spring;

import com.alibaba.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class MulRegisterConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = ApplicationUtil.setApplicationContext("dubbo-mul-register.xml");
        applicationContext.start();
        DemoService bean = applicationContext.getBean(DemoService.class);
        String hello_world = bean.echo("hello world");
        System.out.println("hello_world = " + hello_world);
        LockSupport.park(MulRegisterConsumer.class);
    }
}
