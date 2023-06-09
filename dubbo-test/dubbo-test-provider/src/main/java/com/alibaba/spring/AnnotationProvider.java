package com.alibaba.spring;

import com.alibaba.config.DubboConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class AnnotationProvider {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = ApplicationUtil.setApplicationContext(DubboConfig.class);
//        ClassPathXmlApplicationContext applicationContext = ApplicationUtil.setApplicationContext("dubbo-annotaion.xml");
        applicationContext.start();
        LockSupport.park(AnnotationProvider.class);
    }
}
