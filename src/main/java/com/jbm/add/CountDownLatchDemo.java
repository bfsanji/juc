package com.jbm.add;

import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //倒计时，总数是6,必须要执行任务的时候，再使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"Go Out");
                countDownLatch.countDown(); // -1
            },String.valueOf(i)).start();
        }
        countDownLatch.await(); //等待计数器归零，然后再向下执行
        System.out.println("关门");
    }
}
