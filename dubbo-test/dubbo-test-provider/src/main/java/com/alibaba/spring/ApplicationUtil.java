package com.alibaba.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ApplicationUtil {

    public static ClassPathXmlApplicationContext setApplicationContext(String resource) {
        return new ClassPathXmlApplicationContext("classpath:" + resource);
    }

    public static AnnotationConfigApplicationContext setApplicationContext(Class aClass) {
        return new AnnotationConfigApplicationContext(aClass);
    }

}
