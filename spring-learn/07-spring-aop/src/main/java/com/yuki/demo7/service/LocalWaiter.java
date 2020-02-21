package com.yuki.demo7.service;


import com.yuki.demo7.monitor.Monitor;

public class LocalWaiter implements Waiter{


    public void serveTo() {
        System.out.println("为....服务");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void greetTo(String name) {
//        if(!"yuki".equals(name)){
//            throw new RuntimeException("aaa");
//        }
        System.out.println("have a nice day");

    }
}
