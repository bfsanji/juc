package com.jbm.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        // 适配类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        String o = (String)futureTask.get();
        System.out.println(o);

    }
}

class MyThread implements Callable{

    @Override
    public String call()  {
        System.out.println("call()");
        return "123";
    }
}
