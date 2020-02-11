package com.yuki.demo5.lookup;

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
    private String color;
    private int maxSpeed;
}
