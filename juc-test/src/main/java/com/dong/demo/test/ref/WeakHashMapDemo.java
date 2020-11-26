package com.dong.demo.test.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void MyHashMap(){

        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(2);
        String value ="map";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);


    }

    public static void myWeak(){

        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value ="map";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);

    }

    public static void main(String[] args) {

        myWeak();

    }

}
