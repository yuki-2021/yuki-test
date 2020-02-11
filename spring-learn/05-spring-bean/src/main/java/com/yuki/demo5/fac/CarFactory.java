package com.yuki.demo5.fac;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class CarFactory {

    public Car HongQi() {
        Car car = new Car();
        car.setBrand("红旗CA72");
        return car;
    }

    public static Car byd() {
        Car car = new Car();
        car.setBrand("byd");
        return car;
    }
}
