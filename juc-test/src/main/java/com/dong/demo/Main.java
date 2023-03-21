package com.dong.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 验证
 * sleep 让出cpu,但不会释放锁
 * wait方法会释放锁（所以前提就是线程在执行wait方法之前必须要获得锁，也就意味着只能在同步方法或者同步代码块中调用wait方法），释放锁之后，必须要有另外线程执行notify或者notifyAll，才会唤醒执行了wait方法的线程，该线程才能等待cpu执行
 *
 */
class ShareData{

    public void test(){
        Function<String, Integer> function;
        function = (e->{return 1;});

        BiFunction<Integer,Integer,Integer> biFunction =(x1,x2)->{
            return x1+x2;
        };

        System.out.println(function.apply("123"));
    }

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

    public interface CalFun3<T,U,L, R> {

        R apply(T t, U u,L l);

//    R apply(T t);

    }


    public static void main(String[] args) throws InterruptedException {

        CalFun3<String,String,String,String> calFun3 = ((x1,x2,x3)->{return x1+x2+x3;});

        CalFun2<String,String,String,String> calFun2 = ((x1,x2,x3)->{return x1+x2+x3;});
        CalFun<String,Integer>  calFun = (e->{return e.length();});

        Function<String, Integer> function;
        function = (e->{return e.length();});
        System.out.println(function.apply("123"));;


       String str1 = new StringBuilder("ab").append("s").toString();
        System.out.println(str1==str1.intern());

        System.out.println();

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2==str2.intern());

        System.out.println("============================");
//        String s= "ab";
//        String ss= new String("ab");
        String sb = new StringBuilder("ab").toString();
//        String intern = sb.intern();
//        System.out.println(s==intern);
//        System.out.println(ss==intern);
//        System.out.println(sb==intern);
        System.out.println(sb==sb.intern());

  /*      System.out.println("cpu的个数："+Runtime.getRuntime().availableProcessors());

        ShareData shareData = new ShareData();

        new Thread(()->{
            shareData.add();
        },"AAA").start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            shareData.add2();
        },"BBB").start();

        TimeUnit.SECONDS.sleep(5);*/

    }

}
