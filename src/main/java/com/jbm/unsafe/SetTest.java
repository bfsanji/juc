package com.jbm.unsafe;

import java.lang.invoke.ConstantCallSite;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 解决方法：1、Set<String> set = Collections.synchronizedSet(new HashSet<>());
 */
public class SetTest {
    public static void main(String[] args) {
//        HashSet<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <=10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
                System.out.println(Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }
    }
}
