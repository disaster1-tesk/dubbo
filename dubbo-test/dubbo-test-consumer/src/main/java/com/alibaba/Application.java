package com.alibaba;

import com.alibaba.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws  IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-demo-consumer.xml");
        context.start();
        DemoService demoService = context.getBean(DemoService.class);

        String message = demoService.echo();
        System.out.println("Receive result ======> " + message);
        System.in.read();
        System.exit(0);
    }
}
