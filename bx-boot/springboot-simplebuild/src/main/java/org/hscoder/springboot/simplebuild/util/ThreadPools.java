package org.hscoder.springboot.simplebuild.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;

/**
 * 线程池处理器
 * 
 * @author atp
 *
 */
public class ThreadPools {

    /**
     * 构造线程池
     * 
     * @return
     */
    public static ThreadPoolExecutor newTaskPool() {
        int core = Runtime.getRuntime().availableProcessors() + 1;
        int maxCore = core * 2;
        return newTaskPool(core, maxCore);
    }

    /**
     * 构造线程池
     * 
     * @param corePoolSize
     * @param maximumPoolSize
     * @return
     */
    public static ThreadPoolExecutor newTaskPool(int corePoolSize, int maximumPoolSize) {
        return newTaskPool(corePoolSize, maximumPoolSize, null);
    }

    /**
     * 构造线程池
     * 
     * @param corePoolSize
     * @param maximumPoolSize
     * @param poolName
     * @return
     */
    public static ThreadPoolExecutor newTaskPool(int corePoolSize, int maximumPoolSize, String poolName) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 600L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000));

        // 设置变量
        if (!StringUtils.isEmpty(poolName)) {
            threadPoolExecutor.setThreadFactory(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread tr = new Thread(r, poolName + r.hashCode());
                    return tr;
                }
            });
        }
        return threadPoolExecutor;
    }

    /**
     * 构造调度线程池
     * 
     * @return
     */
    public static ScheduledThreadPoolExecutor newSchedulingPool() {
        return newSchedulingPool(1, null);
    }

    /**
     * 构造调度线程池
     * 
     * @param corePoolSize
     * @return
     */
    public static ScheduledThreadPoolExecutor newSchedulingPool(int corePoolSize) {
        return newSchedulingPool(corePoolSize, null);
    }

    /**
     * 构造调度线程池
     * 
     * @param corePoolSize
     * @param poolName
     * @return
     */
    public static ScheduledThreadPoolExecutor newSchedulingPool(int corePoolSize, String poolName) {

        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(corePoolSize);

        // 设置变量
        if (!StringUtils.isEmpty(poolName)) {
            threadPoolExecutor.setThreadFactory(new ThreadFactory() {
                
                @Override
                public Thread newThread(Runnable r) {
                    Thread tr = new Thread(r, poolName + r.hashCode());
                    return tr;
                }
            });
        }
        return threadPoolExecutor;
    }
}
