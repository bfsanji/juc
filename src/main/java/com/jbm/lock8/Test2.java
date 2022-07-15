package com.jbm.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 3、增加一个普通方法后，打印发短信还是hello ？ 普通方法
 * 4、sendSms延迟四秒，两个对象 先发短信还是打电话？ 打电话
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(()->{
            phone1.sendSms();
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phone2.call();
        },"B").start();
    }

}

class Phone2{

    // synchronized锁的对象是方法的调用者
    //两个方法的调用者都是phone，因此，他们用的锁是同一个，谁先拿到谁执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    public synchronized void call(){
        System.out.println("call");
    }
    //此处没有锁，不是同步方法
    public void hello(){
        System.out.println("hello");
    }
}
