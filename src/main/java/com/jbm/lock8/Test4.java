package com.jbm.lock8;

import java.util.concurrent.TimeUnit;

/**
 *7、 1个静态同步方法，一个普通同步方法，一个对象，先打印？ 打电话 锁的不是一个东西
 * 8、两个对象                            ？ 打电话
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        //两个对象的Class类模板只有一个，static，锁的是Class
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

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
class Phone4{
    // static 静态方法，在类一加载就有了 锁的是Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    //普通同步方法,锁的是调用者
    public synchronized void call(){
        System.out.println("call");
    }
    //此处没有锁，不是同步方法
    public void hello(){
        System.out.println("hello");
    }
}
