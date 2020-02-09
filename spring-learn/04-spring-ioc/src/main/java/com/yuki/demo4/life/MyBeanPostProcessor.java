package com.yuki.demo4.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("car".equals(beanName)) {
            System.out.println("☆☆  BeanPostProcessor.postProcessorBeforeInitalization  ☆☆");
            Car car = (Car) bean;
            car.setColor("white");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("car".equals(beanName)) {
            System.out.println("☆☆  BeanPostProcessor.postProcessorafterInitalization  ☆☆");
            Car car = (Car) bean;
            if(car.getMaxSpeed() >= 150) {
                car.setMaxSpeed(100);
            }
        }
        return bean;
    }
}
