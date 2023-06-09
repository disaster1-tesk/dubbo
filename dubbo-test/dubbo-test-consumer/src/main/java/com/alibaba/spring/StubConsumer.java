package com.alibaba.spring;

import com.alibaba.service.DemoService;
import com.alibaba.service.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class StubConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = ApplicationUtil.setApplicationContext("dubbo-stub.xml");
        applicationContext.start();
        DemoService bean = applicationContext.getBean(DemoService.class);
        String s = bean.setUser(User.builder().age(20).build());
        System.out.println("s = " + s);

        LockSupport.park(MulProtocolConsumer.class);
    }
}
