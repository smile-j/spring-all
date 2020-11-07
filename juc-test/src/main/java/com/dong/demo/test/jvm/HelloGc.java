package com.dong.demo.test.jvm;

import java.util.concurrent.TimeUnit;

public class HelloGc {

    public static void main(String[] args) throws InterruptedException {

        long totalMemory = Runtime.getRuntime().totalMemory();//返回java 虚拟机中的内存总量
        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + totalMemory/(double)1024/1024 +"MB");
        System.out.println("MAX_MEMORY(-Xms) = " + maxMemory + "(字节)、" + maxMemory/(double)1024/1024 +"MB");

    }

}
