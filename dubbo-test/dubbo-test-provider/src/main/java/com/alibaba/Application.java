package com.alibaba;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 */
public class Application {
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
