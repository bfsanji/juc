package com.jbm.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//懒汉式
public class Lazy {
    private static boolean flag = false;
    private Lazy(){
        synchronized (Lazy.class){
            if(flag == false)
                {
                    flag=true;
                }else{
                throw new RuntimeException("不要试图用反射破坏异常");
            }
        }
    }
    private volatile static Lazy lazy;
    public static Lazy getInstance(){
        //双重检测锁模式的懒汉式单例， DCL懒汉式
        //此处第一个判断和第二个判断的作用：如果两个线程同时进来，都判断为空，但是A先拿到锁，B则等待，A第二次判断为空，则创建；等A创建完，B继续执行第二次判断，此时已经不为空了，则直接创建新的。
        if(lazy==null){
            synchronized (Lazy.class){
                if (lazy==null){
                    lazy = new Lazy(); // 此处可能会出现指令重排的情况：如线程A先赋值占用了地址，但是还没完成构造函数，而线程B直接返回 会出现对象尚未初始化错误
                }
            }
        }
        return lazy;
    }
    //多线程并发的情况下可能有问题
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Lazy instance = Lazy.getInstance();
        Constructor<Lazy> declaredConstructor = Lazy.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        Lazy lazy = declaredConstructor.newInstance();
        Lazy lazy1 = declaredConstructor.newInstance();
        System.out.println(lazy == lazy1);
    }
}
