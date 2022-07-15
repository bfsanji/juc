package com.jbm.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 */
public class ReadWriteLocalDemo {
    public static void main(String[] args) {
//        MyCacheLock myCache = new MyCacheLock();
        MyCache myCache = new MyCache();
        //写入
        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        //读取
        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }

    }
}


//自定义缓存
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    //存，写
    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完成");
    }
    //取，读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取ok");
    }
}
//加锁
class MyCacheLock{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    //存，写  写入时只希望同时只有一个线程写
    public void put(String key, Object value){
        lock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }
    //取，读  所有人都可以读
    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {lock.readLock().unlock();
        }
    }
}