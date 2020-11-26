package com.dong.demo.test.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * java -XX:+PrintCommandLineFlags -version
 *
 * 1. Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   (DefNew+Tenured)
 *
 */
public class HelloGc {

    public static void main(String[] args) throws InterruptedException {

//        long totalMemory = Runtime.getRuntime().totalMemory();//返回java 虚拟机中的内存总量   默认为内存的 1/64
////        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量  默认为内存的 1/4
////        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + totalMemory/(double)1024/1024 +"MB");
////        System.out.println("MAX_MEMORY(-Xms) = " + maxMemory + "(字节)、" + maxMemory/(double)1024/1024 +"MB");
//        byte [] bytes = new byte[1024*1024];
//        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        System.out.println("hello world   start !!!");
//        byte [] bytes = new byte[50*1024*1024];
//        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

        int i=1;
        List<User> list = new ArrayList<>();
        try {

            while (i<Integer.MAX_VALUE){
                i++;
                User user = new User(i,String.valueOf(i),String.valueOf(i));
                list.add(new User(i,String.valueOf(i),String.valueOf(i)));
            }
        }catch (RuntimeException e){
            System.out.println("--------------------------------"+i);
            e.printStackTrace();
        }finally {
            System.out.println("--------------------------------"+i);
        }

//        System.out.println("hello world  end !!!");
        //--------------------------------32142
    }

}
