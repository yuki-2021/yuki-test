package com.yuki.feature;

import java.util.concurrent.Callable;

public class MyExecutor {

    public <V> MyFuture<V> execute(final Callable<V> task) {
        //定义锁
        final Object lock = new Object();
        //执行线程
        final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
        thread.start();
        // 获取结果
        MyFuture<V> future = new MyFuture<V>() {
            @Override
            public V get() throws Exception {
                synchronized (lock) {
                    while(!thread.isDone()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    if(thread.getException() != null) {
                        throw thread.getException();
                    }
                    return thread.getResult();
                }
            }
        };

        return future;
    }

    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();
        Callable<Integer> subTask = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int millis = (int) (Math.random() * 1000);
                Thread.sleep(millis);
                return millis;
            }
        };
        MyFuture<Integer> future = executor.execute(subTask);
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
