package com.alibaba.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class StubProvider {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = ApplicationUtil.setApplicationContext("dubbo-stub.xml");
        applicationContext.start();
        LockSupport.park(StubProvider.class);
    }
}
