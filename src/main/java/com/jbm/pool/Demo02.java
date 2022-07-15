package com.jbm.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo02 {
        public static void main(String[] args) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                    2,
                    // 处理核心数
                    Runtime.getRuntime().availableProcessors(),
                    3,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(4),
                    Executors.defaultThreadFactory(),
                    //队列满了，尝试去和最早的竞争，也不会抛出异常！
                    new ThreadPoolExecutor.DiscardOldestPolicy()
            );

            try {
                // 最大承载：maxPoolSize + queue大小
                for (int i = 0; i < 10; i++) {
                    threadPoolExecutor.execute(() -> {
                        System.out.println(Thread.currentThread().getName() + " ok");
                    });
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                threadPoolExecutor.shutdown();
            }
        }
    }

