package com.alibaba.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class MulRegisterProvider {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = ApplicationUtil.setApplicationContext("dubbo-mul-register.xml");
        applicationContext.start();
        LockSupport.park(MulRegisterProvider.class);
    }
}
