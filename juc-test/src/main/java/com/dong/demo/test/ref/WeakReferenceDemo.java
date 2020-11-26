package com.dong.demo.test.ref;

import java.lang.ref.WeakReference;

/**
 *有GC就回收
 */
public class WeakReferenceDemo {



    public static void main(String[] args) {

        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1 = null ;
        System.gc();
        System.out.println("======================================");
        System.out.println(o1);
        System.out.println(weakReference.get());


    }

}
