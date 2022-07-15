package com.jbm.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

//不保证原子性
public class VDemo2 {
    private  volatile static AtomicInteger num= new AtomicInteger(0);
//    private  volatile static int num= 1;
    public static void add(){
        //  普通 int num ++不是原子性操作 包括一下三步
        //1、获取这个值  2、执行+1  3、写回这个值
        num.getAndIncrement(); //+1方法
//        num++;
    }

    public static void main(String[] args) {
        //理论结果为20000
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                  add();
                }
            }).start();
        }
        while(Thread.activeCount()>2){ //两个线程默认存在main 和gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
}
