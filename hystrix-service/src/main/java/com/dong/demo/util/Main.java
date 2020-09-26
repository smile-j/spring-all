package com.dong.demo.util;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String [] args){

        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());

    }

}
