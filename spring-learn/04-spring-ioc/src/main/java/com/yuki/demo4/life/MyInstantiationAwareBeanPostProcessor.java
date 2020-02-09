package com.yuki.demo4.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("☆☆☆☆-- InstantiationAwareBeanPostProcessor.postBeforeInstantiation --☆☆☆☆");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("☆☆☆☆-- InstantiationAwareBeanPostProcessor.postAfterInstantiation --☆☆☆☆");
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("☆☆☆☆-- InstantiationAwareBeanPostProcessor.postPropertyValues --☆☆☆☆");
        return pvs;
    }
}
