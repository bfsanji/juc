package com.jbm.pool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// Executors工具类 三大方法
//使用了线程池之后，使用线程池来创建线程
public class Demo01 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);// 创建一个固定的线程池大小
        ExecutorService threadPool2 = Executors.newCachedThreadPool();// 可伸缩的

        try {
            for (int i = 0; i < 100; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }

    }
}
