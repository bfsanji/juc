package com.jbm.function;

import jdk.nashorn.internal.ir.CallNode;
/**
 * 断定型接口，有一个输入参数，返回值只能是布尔值
 */
import java.util.function.Predicate;

public class Demo02 {
    public static void main(String[] args) {
        //判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {

                return o.isEmpty();
            }
        };

        Predicate<String> predicate1 = str->str.isEmpty();
        Predicate<String> predicate2 = String::isEmpty;
        System.out.println(predicate1.test(""));
    }
}
