package com.yuki.demo7.service;


import com.yuki.demo7.monitor.Monitor;

public class NativeWaiter implements Waiter{


    public void serveTo() {

        // 调用性能监控
        Monitor.begin(Thread.currentThread().getStackTrace()[2].getMethodName());
        // 业务逻辑
        System.out.println("为....服务");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 结束监控
        Monitor.end();
    }

    public void greetTo(String name) {
        System.out.println("have a nice day");
    }
}
