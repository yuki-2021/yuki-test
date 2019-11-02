package com.yuki.test1;

public class OrderDemo {

    private int a = 100;
    private int b;
    {
        b = 200;
        System.out.println("b = 200");
    }

    public OrderDemo() {
        System.out.println("construct");
    }

    public static void main(String[] args) {
        OrderDemo orderDemo = new OrderDemo();
        System.out.println(orderDemo);
    }
}
