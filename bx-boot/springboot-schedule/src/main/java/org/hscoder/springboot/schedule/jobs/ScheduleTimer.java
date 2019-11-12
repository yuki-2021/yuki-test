package org.hscoder.springboot.schedule.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 利用@Scheduled注解实现定时器
 * 
 * @author atp
 *
 */
@Component
public class ScheduleTimer {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTimer.class);

    /**
     * 每10s
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void onFixDelay() {
        logger.info("schedule job on every 10 seconds");
    }

    /**
     * 每分钟的0秒执行
     */
    @Scheduled(cron = "0 * * * * *")
    public void onCron() {
        logger.info("schedule job on every minute(0 second)");
    }

    /**
     * 启用定时器配置
     * 
     * @author atp
     *
     */
    @Profile("async")
    @Configuration
    @EnableScheduling
    public class ScheduleConfig implements SchedulingConfigurer {
        
        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
            taskRegistrar.setScheduler(taskExecutor());
        }
     
        @Bean(destroyMethod="shutdown")
        public Executor taskExecutor() {
            return Executors.newScheduledThreadPool(50);
        }
    }
}
