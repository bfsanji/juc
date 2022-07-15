package com.jbm.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功");
        });

        for (int i = 1; i <=7 ; i++) {
           // lambda能操作到 i 嘛 拿不到，在Lambda表达式中可以捕获静态变量和实例变量，但是如果想要捕获局部变量的时候就需要声明成final的，即使我们不主动声明，编译器也会为我们自动声明成final的，不能再重新赋值。也就是Lambda表达式中访问的局部变量(隐式被声明为final的)是可读不可写的，但是Lambda表达式中访问的实例变量和静态变量是可读可写的。
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"个龙珠");
                try {
                    cyclicBarrier.await();//等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
