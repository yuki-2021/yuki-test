package com.yuki.demo4.life;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean{
    private String brand;
    private String color;
    private int maxSpeed;


    private BeanFactory beanFactory;
    private String beanName;

    public void introduce() {
        System.out.println(toString());
    }

    public void setBeanName(String name) {
        System.out.println("---- BeanameAware.setBeanName ----");
        System.out.println(name);
        this.beanName = name;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("---- BeanFactoryAware.setBeanFactory ----");
        System.out.println(beanFactory);
        this.beanFactory = beanFactory;
    }


    public void afterPropertiesSet() throws Exception {
        System.out.println("---- InitalizingBean.afterPropertiesSet ----");
    }

    public void destroy() throws Exception {
        System.out.println("---- DisposableBean.destory ----");
    }

    public void init() {
        System.out.println("----init and set maxSpeed 240----");
        this.maxSpeed = 240;
    }

    public void desory() {
        System.out.println("---  yuki destory method ----");
    }
}
