package com.yuki.demo7.monitor;

public class Monitor {

    private static ThreadLocal<Performance> performances = new ThreadLocal<Performance>();
    private static ThreadLocal<MonitorItem> monitorItems = new ThreadLocal<MonitorItem>();

    public static void begin(String methodName) {
        begin(methodName, Thread.currentThread().getName());
    }

    public static void begin(String methodName, String thradName) {
        MonitorItem monitorItem = new MonitorItem(methodName, thradName);
        System.out.println("======开始监控=====["+ monitorItem.getThreadName()+"] - ["+ monitorItem.getMethodName()+"]");
        performances.set(new Performance());
        monitorItems.set(monitorItem);
    }

    public static void end() {
        MonitorItem monitorItem = monitorItems.get();
        System.out.println("======结束监控=====["+ monitorItem.getThreadName()+"] - ["+ monitorItem.getMethodName()+"]");
        performances.get().setEnd(System.currentTimeMillis());
        showPerformance();
    }

    public static void showPerformance() {
        System.out.println("======性能=====");
        performances.get().printPerform();
    }
}
