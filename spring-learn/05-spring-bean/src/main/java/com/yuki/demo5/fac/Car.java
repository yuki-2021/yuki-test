package com.yuki.demo5.fac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Car {
    private String brand;
    private String corp;
    private int maxSpeed;
}
