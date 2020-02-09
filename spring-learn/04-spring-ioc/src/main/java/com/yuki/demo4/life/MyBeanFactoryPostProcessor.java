package com.yuki.demo4.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition car = beanFactory.getBeanDefinition("car");
        car.getPropertyValues().removePropertyValue("brand");
        car.getPropertyValues().addPropertyValue("brand","奇瑞QQ");
    }
}
