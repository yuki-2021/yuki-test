package com.yuki.test1;

public class ArithOperation {
    public static void main(String[] args) {

        int a = 2147483647*2;
        System.out.println(a);

        long b = 2147483647*2;
        System.out.println(b);

        //小数计算不精确
        float c = 0.1f * 0.1f;
        System.out.println(c);
    }
}
