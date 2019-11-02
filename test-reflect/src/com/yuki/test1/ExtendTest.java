package com.yuki.test1;

class ExtendTest {
    public static void main(String[] args) {
        Base base = new Child();
        System.out.println(base.b);
        base.say();
    }
}

class Base{
    public int b = 12;

    public void say(){
        System.out.println("base");
    }
}

class Child extends Base{
    public int b = 13;

    @Override
    public void say(){
        System.out.println("child");
    }
}
