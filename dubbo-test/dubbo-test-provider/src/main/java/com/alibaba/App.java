package com.alibaba;

import com.alibaba.config.DubboConfig;
import com.alibaba.service.DemoService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
//        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(DubboConfig.class);
//        configApplicationContext.start();
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        ClassPathResource resource = new ClassPathResource("spring-dubbo.xml");
//        reader.loadBeanDefinitions(resource);
//        beanFactory.getBean(DemoService.class).echo();
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-dubbo.xml");
        applicationContext.start();
        System.in.read();
    }
}
