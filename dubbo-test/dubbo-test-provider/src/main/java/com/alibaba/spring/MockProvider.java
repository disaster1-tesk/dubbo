package com.alibaba.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class MockProvider {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = ApplicationUtil.setApplicationContext("dubbo-mock.xml");
        applicationContext.start();
        LockSupport.park(MulProtocolProvider.class);
    }
}
