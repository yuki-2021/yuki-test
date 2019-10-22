package com.yuki.Test15.cooper;


import java.util.concurrent.Callable;

/**
 * 异步结果
 *
 * @author
 */
public class MyExecutor {

    //表示执行结果
    public interface MyFuture <V> {
        V get() throws Exception ;
    }

    //执行任务
    public <V> MyFuture<V> execute(final Callable<V> task){
        //1. 创建线程
        final Object lock = new Object();
        final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
        thread.start();
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
        //2. 包装返回结果
    }

    public static void main(String[] args) {
        //执行器
        MyExecutor executor = new MyExecutor();
        //callable任务
        Callable<Integer> subTask = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int millis = (int) (Math.random() * 1000);
                Thread.sleep(millis);
                return millis;
            }
        };
        //执行
        MyFuture<Integer> future = executor.execute(subTask);
        //获取结果
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static class ExecuteThread<V> extends Thread {
        private V result = null;
        private Exception exception = null;
        private boolean done = false;
        private Callable<V> task;
        private Object lock;
        public ExecuteThread(Callable<V> task, Object lock) {
            this.task = task;
            this.lock = lock;
        }
        @Override
        public void run() {
            try {
                result = task.call();
            } catch (Exception e) {
                exception = e;
            } finally {
                synchronized (lock) {
                    done = true;
                    lock.notifyAll();
                }
            }
        }
        public V getResult() {
            return result;
        }
        public boolean isDone() {
            return done;
        }
        public Exception getException() {
            return exception;
        }
    }
}
