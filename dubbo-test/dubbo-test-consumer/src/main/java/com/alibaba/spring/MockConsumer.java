package com.alibaba.spring;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class MockConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = ApplicationUtil.setApplicationContext("dubbo-mock.xml");
        applicationContext.start();
        DemoService bean = applicationContext.getBean(DemoService.class);
        RpcContext.getServerContext().setAttachment("isMock","true");
        RpcContext.getServerContext().set("isMock","true");
        RpcContext.getContext().setAttachment("isMock","true");
        String hello_world = bean.echo("hello world");
        System.out.println(bean.setUser(User.builder().build()));
        System.out.println("hello_world = " + hello_world);
        LockSupport.park(MulProtocolConsumer.class);
    }
}
