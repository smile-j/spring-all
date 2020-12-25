package com.dong.demo.jdk8.lambad;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * 变量引用
 *
 * java 传的是值，不是引用
 */
public class VarDemo {



    public static void main(String[] args) {

//        String str= "hello";
////        str = "";
//        Consumer<String> consumer = (s)-> System.out.println(s+str);
//        consumer.accept("123");

        HashMap map = new HashMap();
        map.put("","");

        Hashtable hashtable = new Hashtable();
        ConcurrentHashMap<String,String> stringStringConcurrentHashMap = new ConcurrentHashMap<>();
        stringStringConcurrentHashMap.put("","");
        stringStringConcurrentHashMap.size();
    }

}
