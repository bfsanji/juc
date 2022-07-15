package com.jbm.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5、增加两个静态的同步方法，只有一个对象，先打印哪个？ 发短信
 * 6、两个对象                                 ？ 发短信 因为锁的是Class
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        //两个对象的Class类模板只有一个，static，锁的是Class
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(()->{
            phone1.sendSms();
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phone2.call();
        },"B").start();
    }

}
// Phone3只有唯一一个Class对象
class Phone3{
    // static 静态方法，在类一加载就有了 锁的是Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    public static synchronized void call(){
        System.out.println("call");
    }
    //此处没有锁，不是同步方法
    public void hello(){
        System.out.println("hello");
    }
}
