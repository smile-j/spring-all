package com.dong.demo.test.jdksource;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(3));
        System.out.println(tableSizeFor(7));
//        testHashMap();
    }

    public static void testHashMap(){

        HashMap<String,String> map = new HashMap<>();
        String put1 = map.put("11", "12");
        String put2 = map.put("11", "33");
        System.out.println(put1);
        System.out.println(put2);
        String s1 = map.putIfAbsent("22", "23");
        System.out.println(s1);
        String s2 = map.putIfAbsent("22", "33");
        System.out.println(s2);
        map.size();
        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+"-----"+entry.getValue());
        }

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

