package com.yuki.demo5.anno;

import com.yuki.demo5.scope.Boss;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BossBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) throws BeansException {

        DefaultListableBeanFactory fac = (DefaultListableBeanFactory) bf;
        // 定义BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(Boss.class);
        beanDefinitionBuilder.addPropertyValue("name","yukiboss");

        // 注册BeanDefinition
        fac.registerBeanDefinition("boss",beanDefinitionBuilder.getBeanDefinition());


    }
}
