package com.yuki.demo5.scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.FactoryBean;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarFactoryBean implements FactoryBean {
    String carInfo;
    public Object getObject() throws Exception {
        String[] split = carInfo.split(",");
        Car car = new Car();
        car.setBrand(split[0]);
        car.setColor(split[1]);
        car.setMaxSpeed(Integer.parseInt(split[2]));
        return car;
    }

    public Class<?> getObjectType() {
        return Car.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
