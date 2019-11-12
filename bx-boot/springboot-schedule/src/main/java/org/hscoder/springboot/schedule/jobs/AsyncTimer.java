package org.hscoder.springboot.schedule.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Profile("async")
@Component
public class AsyncTimer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTimer.class);

    @Autowired
    private AsyncTask task;

    @Override
    public void run(String... args) throws Exception {
        long t1 = System.currentTimeMillis();
        task.doAsyncWork();

        long t2 = System.currentTimeMillis();
        logger.info("async timer execute in {} ms", t2 - t1);
    }

    @Component
    public static class AsyncTask {

        private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

        @Async
        public void doAsyncWork() {
            long t1 = System.currentTimeMillis();

            try {
                Thread.sleep((long) (Math.random() * 5000));
            } catch (InterruptedException e) {
            }

            long t2 = System.currentTimeMillis();
            logger.info("async task execute in {} ms", t2 - t1);
        }
    }

    /**
     * 启用定时器配置
     * 
     * @author atp
     *
     */
    @Profile("async")
    @Configuration
    @EnableAsync
    public static class ScheduleConfig implements AsyncConfigurer {

        @Bean
        public ThreadPoolTaskScheduler taskScheduler() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(60);
            scheduler.setThreadNamePrefix("AsyncTask-");
            scheduler.setAwaitTerminationSeconds(60);
            scheduler.setWaitForTasksToCompleteOnShutdown(true);
            return scheduler;
        }

        @Override
        public Executor getAsyncExecutor() {
            return taskScheduler();
        }

        @Override
        public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
