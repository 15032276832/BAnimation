package com.bianbian.frameanimation.threadPool;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private static Map<String, ThreadPoolExecutor> map = new HashMap<>();

    public static Future submit(String taskName, Runnable runnable) {
        if (!map.containsKey(taskName)) {
            map.put(taskName, getPoolExecutor(taskName));
        }
        return map.get(taskName).submit(runnable);
    }

    private static ThreadPoolExecutor getPoolExecutor(String taskName) {
        return new ThreadPoolExecutor(
                10,//核心线程数
                10,//最大线程数
                0,//线程数大于核心，多余空闲线程存活时间
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingDeque<>(10),//线程队列
                /* 需将 runnable new Thread的构造方法中 */
                runnable -> new Thread(runnable, taskName),//自定义名称线程池
                new RejectHandler(taskName)//被拒策略
        );
    }

    private static class RejectHandler implements RejectedExecutionHandler {
        private String threadName;

        public RejectHandler(String threadName) {
            this.threadName = threadName;
        }

        /* 当线程池满，新提交的任务 runnable，会在这里返回；executor 为执行此任务的线程池 */
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            Log.e("ThreadPoolCustomAnim", "rejectedExecution");
            if (map.containsKey(threadName)) {
                map.get(threadName).shutdown();
                map.remove(threadName);
            }
            executor.shutdown();
            submit(threadName, runnable);
        }
    }
}
