package com.dong.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证
 * sleep 让出cpu,但不会释放锁
 * wait方法会释放锁（所以前提就是线程在执行wait方法之前必须要获得锁，也就意味着只能在同步方法或者同步代码块中调用wait方法），释放锁之后，必须要有另外线程执行notify或者notifyAll，才会唤醒执行了wait方法的线程，该线程才能等待cpu执行
 *
 */
class ShareData{

    private volatile AtomicInteger atomicInteger = new AtomicInteger();

    public synchronized void add(){
        System.out.println(Thread.currentThread().getName()+"\t start");
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+atomicInteger.incrementAndGet());
        System.out.println(Thread.currentThread().getName()+"\t end");
    }

    public synchronized void add2(){
        System.out.println(Thread.currentThread().getName()+"\t start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifyAll();
        System.out.println(Thread.currentThread().getName()+"\t"+atomicInteger.incrementAndGet());
        System.out.println(Thread.currentThread().getName()+"\t end");
    }

    public synchronized void add3(){
        System.out.println(Thread.currentThread().getName()+"\t start");
        System.out.println(Thread.currentThread().getName()+"\t"+atomicInteger.incrementAndGet());
        System.out.println(Thread.currentThread().getName()+"\t end");
    }

}

public class Main {


    public static void main(String[] args) throws InterruptedException {

        System.out.println("cpu的个数："+Runtime.getRuntime().availableProcessors());

        ShareData shareData = new ShareData();

        new Thread(()->{
            shareData.add();
        },"AAA").start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            shareData.add2();
        },"BBB").start();

        TimeUnit.SECONDS.sleep(5);

    }

}
