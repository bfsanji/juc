package com.jbm.function;

import java.util.function.Function;
//Function 函数式接口，有一个输入参数，有一个输出
//只要是函数式接口 可以用lambda表达式简化
public class Demo1 {
    public static void main(String[] args) {
        Function<String,String> function = new Function<String,String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };
        System.out.println(function.apply("asd"));

        Function<String,String> function1 = s->s;;
        System.out.println(function1.apply("as"));
    }
}
