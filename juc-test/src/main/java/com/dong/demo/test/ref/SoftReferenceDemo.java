package com.dong.demo.test.ref;

import cn.hutool.bloomfilter.bitMap.BitMap;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 软引用 空间够就保留，空间不够就回收
 */
public class SoftReferenceDemo {

    public static void softRef_Memory_Enough(){

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());

    }
    public static void softRef_Memory_NotEnough() {

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        try {
            byte [] bytes = new byte[20*1024*1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }


    }




    public static void main(String[] args) {
//        softRef_Memory_Enough();
        softRef_Memory_NotEnough();

        //应用场景

        Map<String, SoftReference<BitMap>> imageCasche = new HashMap<>();

    }

}
