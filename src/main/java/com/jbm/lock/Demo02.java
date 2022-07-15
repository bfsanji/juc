package com.jbm.lock;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {
    public static void main(String[] args) {
        Phone1 phone = new Phone1();

        new Thread(()->{
            phone.sms();
        },"A").start();
        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}
class Phone1{
    Lock lock = new ReentrantLock();
    public void sms(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"sms");
            call(); //这里也有锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public synchronized void call(){
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+"call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}