package com.yuki.test1;

public class StackOutTest {

    public static long fab(int n){
        return n == 1 ? 1 : n * fab(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(fab(100000));
    }

}
