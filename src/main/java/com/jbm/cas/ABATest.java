package com.jbm.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
public class ABATest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        // 线程1没有感知到线程2对变量的中间操作
        new Thread(() -> {
            int value = atomicInteger.get();
            System.out.println("thread1 read value:" + value);
            // 阻塞1s
            LockSupport.parkNanos(1000000000L);

            if (atomicInteger.compareAndSet(value, 3)){
                System.out.println("thread1 update from " + value + " to 3");
            }else {
                System.out.println("thread1 update fail!");
            }
        }, "Thread1").start();

        new Thread(() -> {
            int value = atomicInteger.get();
            System.out.println("thread2 read value: " + value);
            // thread2通过cas修改value的值2
            if (atomicInteger.compareAndSet(value, 2)){
                System.out.println("thread2 update from " + value + " to 2");
                // do something
                value = atomicInteger.get();
                System.out.println("thread2 read value: "+ value);
                // Thread2通过CAS修改value值为1
                if (atomicInteger.compareAndSet(value, 1)){
                    System.out.println("thread2 update from " + value + " to 1");
                }
            }
        }, "Thread2").start();

    }
}
