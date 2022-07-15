package com.jbm.bq;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestDemo {
    public static void main(String[] args) {

    }

    /**
     * 抛出异常
     */
    public static void test1(){
        // 需要些队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("a"));
        blockingQueue.remove();
    }

    /**
     * 等待，阻塞（一直阻塞）
     * */
    @Test
    public void test3() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        // 一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 队列没有位置了，一直阻塞
//        blockingQueue.put("d");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        // 没有这个元素，一直阻塞
//        blockingQueue.take();
    }

    /**
     * 等待，阻塞（超时等待）
     *
     * */
    @Test
    public void test4() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // 等待超过2秒就退出
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);
        System.out.println("============");
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        // 等待超过2秒就退出
        blockingQueue.poll(2, TimeUnit.SECONDS);

    }

    @Test
    public void test5() throws InterruptedException {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(4);
        blockingQueue.add("a");
        blockingQueue.offer("b");
        blockingQueue.put("c");
        blockingQueue.put("c");
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.peek());
        System.out.println("===========");
        blockingQueue.remove();
        blockingQueue.poll();
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }
}

