package com.yuki.thread;

import java.util.concurrent.Executors;

public class ExecutorService {

    public static void main(String[] args) {
        java.util.concurrent.ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
    }
}
