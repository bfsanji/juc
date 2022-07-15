package com.jbm.lock;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {

        new Thread(new MyThread("A","B"),"T1").start();
        new Thread(new MyThread("B","A"),"T2").start();
    }
}
class MyThread implements Runnable{
    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"lock"+lockA+"=>"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"lock"+lockB+"=>"+lockA);
            }
        }
    }
}