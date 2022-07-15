package com.jbm.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {
    public static void main(String[] args) {
        Data1 data = new Data1();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }},"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }},"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }},"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }},"D").start();
    }
}
//判断等待 ..业务..通知
class Data1{
    //数字，资源类
    private int num=0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //condition.await(); 等待
    //condition.signalAll(); 唤醒
    //+1
    public void increment() throws InterruptedException {

        try {
            lock.lock();
            while(num!=0){
                //等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            //通知其他线程，+1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }




    }
    //-1
    public void decrement() throws InterruptedException {
        try {
            lock.lock();
            while(num == 0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            //通知其他线程，-1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
