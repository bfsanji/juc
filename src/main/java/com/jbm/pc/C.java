package com.jbm.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *A执行完电泳B，B执行完调用C，C执行完调用A
 */
public class C {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
            data3.printB();
        }},"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"C").start();
    }
}

class Data3{//资源类loc
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int num = 1; //1 A执行；2 B执行 ；3 C执行
    public void printA(){
        try {
            lock.lock();
            //业务，判断->执行->通知
            while(num!=1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAA");
            //唤醒，唤醒指定的人B
            num =2;
           condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void printB(){
        try {
            lock.lock();
            //业务，判断->执行->通知
            while(num!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBB");
            num=3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        try {
            lock.lock();
            //业务，判断->执行->通知
            while(num!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCC");
            num=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

