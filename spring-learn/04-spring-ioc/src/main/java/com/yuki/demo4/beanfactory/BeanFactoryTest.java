package com.yuki.demo4.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

public class BeanFactoryTest {

    public static void main(String[] args) throws IOException {
        // 1. 加载资源
        PathMatchingResourcePatternResolver resourceResolver =
                new PathMatchingResourcePatternResolver();
        Resource resource =
                resourceResolver.getResource("classpath:beanfactory/beanFactory.xml");
        System.out.println(resource.getURL().toString());

        // 2. 创建BeanFactory 和 BeanDefinitionReader
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(factory);

        // 3. 初始化Bean
        beanDefinitionReader.loadBeanDefinitions(resource);

        // 4. 获取Bean
        Car car = factory.getBean(Car.class);
        System.out.println(car);
    }
}
