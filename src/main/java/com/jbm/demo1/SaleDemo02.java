package com.jbm.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleDemo02 {

    public static void main(String[] args) {
        //并发：多线程操作同一个资源类,把资源类丢入线程
        Ticket1 ticket = new Ticket1();

        // @FunctionalInterface 函数式接口 jdk1.8 lambda表达式(参数)->{代码}
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale();}, "A").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale();}, "B").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale();}, "C").start();


    }
}
//资源类
//1、new ReentrantLock();
//2、Lock.lock() 加锁
//3、lock.unlock() 解锁
class Ticket1{
    //属性、方法
    private int number = 50;
    Lock lock = new ReentrantLock();
    //卖票的方式
    public synchronized void sale(){
        lock.lock();

        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了" +(number--)+"票,剩余:"+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
