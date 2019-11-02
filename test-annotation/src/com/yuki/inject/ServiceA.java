package com.yuki.inject;

public class ServiceA {

    @SimpleInject
    ServiceB b;


    public void callB(){
        b.action();
    }
}
