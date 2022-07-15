package com.jbm.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1", "2", "3");
        strings.forEach(System.out::println);

        //并发下 ArrayList不安全
        /**
         * 解决方案：1、 变为Vector
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         */
        //CopyOnWrite 写入时复制  COW 计算机程序设计领域的一种优化策略
        // 多个线程调用的时候，list,读取的时候是固定的，写入(可能存在覆盖操作)
        // 在写入的时候避免覆盖，造成数据问题
        //CopyOnWriteArrayList比Vector效率高，因为Vector使用的是synchronized 前者用的Lock
        // 读写分离的思想
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);},String.valueOf(i)).start();

        }
    }
}
