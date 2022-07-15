package com.jbm.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private static volatile int num =0;
    public static void main(String[] args) {
        //主线程

        new Thread(()->{//线程1 对主内存的变化不知道 不可见
            while(num==0){

            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}
