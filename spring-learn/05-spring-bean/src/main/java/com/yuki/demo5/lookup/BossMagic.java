package com.yuki.demo5.lookup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BossMagic implements ApplicationContextAware {
    Car car;
    ApplicationContext context;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }


    public Car getCar() {
        return context.getBean(Car.class);
    }
}
