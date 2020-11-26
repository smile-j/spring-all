package com.dong.demo.test.jvm;

import java.util.Random;

/**
 * -Xms10m -Xmx10m
 *
 */
public class JavaHeapSpaceDemo {

    //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    public static void main(String[] args) {
        String str ="hello world";
        while (true){
            str+=str + new Random().nextInt(111111)+new Random().nextInt(333333333);
            str.intern();
        }

    }

}
