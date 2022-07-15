package com.jbm.unsafe;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class MapTest {
    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <=10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
