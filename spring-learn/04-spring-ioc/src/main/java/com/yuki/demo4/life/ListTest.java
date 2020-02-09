package com.yuki.demo4.life;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ListTest {

    public static void main(String[] args) {
        PathMatchingResourcePatternResolver resourceResolver =
                new PathMatchingResourcePatternResolver();
        Resource resource = resourceResolver.getResource("life/beanFactory.xml");


        BeanFactory fac =
                new XmlBeanFactory(resource);
        ((ConfigurableBeanFactory)fac)
                .addBeanPostProcessor(fac.getBean(MyInstantiationAwareBeanPostProcessor.class));
        ((ConfigurableBeanFactory)fac)
                .addBeanPostProcessor(fac.getBean(MyBeanPostProcessor.class));

        Car car = fac.getBean(Car.class);
        System.out.println(car);
    }
}
