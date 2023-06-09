package com.alibaba.spring;

import com.alibaba.config.DubboConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 */
public class XmlApplication {

    public static void main(String[] args) throws IOException {
//        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(DubboConfig.class);
//        configApplicationContext.start();
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        ClassPathResource resource = new ClassPathResource("spring-dubbo.xml");
//        reader.loadBeanDefinitions(resource);
//        beanFactory.getBean(DemoService.class).echo();
        ClassPathXmlApplicationContext classPathXmlApplicationContext = ApplicationUtil.setApplicationContext("spring-dubbo.xml");
        classPathXmlApplicationContext.start();
        System.in.read();
    }
}
