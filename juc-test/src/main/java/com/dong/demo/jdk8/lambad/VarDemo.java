package com.dong.demo.jdk8.lambad;

import java.util.function.Consumer;

/**
 * 变量引用
 */
public class VarDemo {



    public static void main(String[] args) {

        String str= "hello";
//        str = "";
        Consumer<String> consumer = (s)-> System.out.println(s+str);
        consumer.accept("123");

    }

}
