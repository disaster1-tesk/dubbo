package com.alibaba.spring;

import com.alibaba.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class XmlConsumerApplication {

    public static void main(String[] args) throws  IOException {
        ClassPathXmlApplicationContext context = ApplicationUtil.setApplicationContext("dubbo-demo-consumer.xml");
        context.start();
        DemoService demoService = context.getBean(DemoService.class);
        String message = demoService.echo();
        System.out.println("Receive result ======> " + message);
        System.in.read();
        System.exit(0);
    }
}
