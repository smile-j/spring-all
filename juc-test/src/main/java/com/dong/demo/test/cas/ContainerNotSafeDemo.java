package com.dong.demo.test.cas;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;

/**
 * 不安全的集合类
 *  ArrayList
 *
 */
public class ContainerNotSafeDemo {


    public static void main(String[] args) {

        setNotHashMap();
    }

    public static void setNotHashMap(){
        Map<String,String> map ;
//        map = new HashMap<>();
         map = new ConcurrentHashMap<>();
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        for (int i=1;i<=30;i++){

            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }

    public static void setNotSet(){
        //Set<String> set =new HashSet<>();
        //Set<String> set =Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet();
        for (int i=1;i<=30;i++){

            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();

        }
        new HashSet<>();
    }

    public static void listNotSafe() {

//        List<String> list = new ArrayList<String>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list =new  CopyOnWriteArrayList();
        for (int i=1;i<=30;i++){

            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();

        }

        /**
         * 故障：java.util.ConcurrentModificationException
         *
         *
         *
         */

    }

}
