package com.jbm.cas;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    // CAS compareAndSet 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //期望、更新
        //如果我期望的值达到了，那么就更新，否则，就不更新 CAS是cpu的并发原语
        atomicInteger.compareAndSet(2020,2021);
        System.out.println(atomicInteger.get());
        atomicInteger.compareAndSet(2020,2022);
        System.out.println(atomicInteger.get());
    }
}
