package com.yuki.demo4.reflect;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    private String brand;
    private String color;
    private int maxSpeed;

    public void introduce() {
        System.out.println(toString());
    }
}
