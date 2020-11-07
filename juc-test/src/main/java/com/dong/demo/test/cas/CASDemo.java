package com.dong.demo.test.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
//        atomicInteger.getAndIncrement()
        System.out.println(atomicInteger.compareAndSet(5,2019)+"\t  current value :"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024)+"\t  current value :"+atomicInteger.get());


    }
}
