package com.yuki.time.task.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SpringTaskDemo {

//    @Scheduled(cron = "0/1 * * * * *")
    public void scheduled1(){
        System.out.println("每秒执行一次定时任务" + LocalDateTime.now());
    }

    /**
     * 到下一个时间点，当前任务没有执行完毕，会继续执行，然后判断
     *
     */
//    @Scheduled(cron = "0/10 * * * * *")
    public void scheduled2(){
        System.out.println("每秒执行一次定时任务" + LocalDateTime.now());
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


//    @Async
//    @Scheduled(cron = "0/10 * * * * *")
    public void scheduled3(){
        System.out.println("每秒执行一次定时任务" + LocalDateTime.now());
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Scheduled(fixedRate = 10000)
    public void scheduled4() throws InterruptedException {
        System.out.println("每秒执行一次定时任务" + LocalDateTime.now());
        Thread.sleep(15000);
    }

//    @Scheduled(fixedDelay = 10000)
    public void scheduled5() throws InterruptedException {
        //10 + 15
        System.out.println("每秒执行一次定时任务" + LocalDateTime.now());
        Thread.sleep(15000);

    }
}
