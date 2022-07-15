package com.jbm.lock8;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 1、标准情况下，两个线程先打印sendSms 还是 call
 * 2、sendSms延迟4s，两个线程先打印sendSms 还是call
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSms();
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phone.call();
        },"B").start();
    }

}

class Phone{

    // synchronized锁的对象是方法的调用者
    //两个方法的调用者都是phone，因此，他们用的锁是同一个，谁先拿到谁执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    public synchronized void call(){
        System.out.println("call");
    }
}
