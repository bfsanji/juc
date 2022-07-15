package com.jbm.bq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列与其他blockQueue不一样，SynchronousQueue不存储元素
 * put了一个元素，必须从里面take取出来，否则不能再put
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>(); //同步队列
        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+"put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
