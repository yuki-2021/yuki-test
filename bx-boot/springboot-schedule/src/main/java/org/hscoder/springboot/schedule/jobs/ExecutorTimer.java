package org.hscoder.springboot.schedule.jobs;

import org.hscoder.springboot.simplebuild.util.ThreadPools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 自启动线程池调度
 * 
 * @author atp
 *
 */
@Profile("async")
@Component
@Order(1)
public class ExecutorTimer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorTimer.class);

    private ScheduledExecutorService schedulePool;

    @Override
    public void run(String... args) throws Exception {
        logger.info("start executor tasks");

        schedulePool = ThreadPools.newSchedulingPool(2);

        schedulePool.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                logger.info("run on every minute");

            }
        }, 5, 60, TimeUnit.SECONDS);
    }
}
