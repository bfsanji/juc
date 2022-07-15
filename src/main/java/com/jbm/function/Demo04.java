package com.jbm.function;

import java.util.function.Supplier;

/**
 * 供给型接口，没有参数但是有返回值
 */
public class Demo04 {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "2014";
            }
        };
        System.out.println(supplier.get());
        Supplier<String> supplier1 = ()->{
            return "1234";
        };
        System.out.println(supplier1.get());
    }
}
