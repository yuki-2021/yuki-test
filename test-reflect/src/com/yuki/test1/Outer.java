package com.yuki.test1;

public class Outer {

    public static class Inner{
        public void say(){
            System.out.println("inner");
        }
    }

    public static void main(String[] args) {
        Inner inner = new Inner();
        System.out.println(inner);
    }
}
