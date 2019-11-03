package com.yuki.time.task.timer;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行定时任务:" + LocalDateTime.now());
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,5000,2000);
    }
}
