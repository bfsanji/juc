package com.jbm.function;

import java.util.function.Consumer;

/**
 * Consumer消费型接口，只有输入，没有返回值
 */
public class Demo03 {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("jj" + s);
            }
        };
        consumer.accept("jbm");

        Consumer<String> consumer1 = str->
            System.out.println("bb" +str);
        consumer1.accept("jbm");
    }
}
