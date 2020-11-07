package com.dong.demo.test.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyDate{
//    int number=0;
    volatile int  number=0;
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addTo60(){
        this.number = 60;
    }

    public  void addPlusPlus(){
        number++;
    }
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 *  1. 验证volatitle的可见性
 *  2. 验证volatile不保证原子性  怎么解决(synchronized)
 *  3. 有序性
 *
 */
public class VolatileDemo {

    public static void main(String [] args){

        MyDate myDate = new MyDate();
        for(int i=0;i<20;i++){
            new Thread(()->{
                for(int j=0;j<1000;j++){
                    myDate.addPlusPlus();
                    myDate.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        //需要等待上面20个线程全部计算完毕
        int i=0;
        while (Thread.activeCount()>2){
            i++;
            Thread.yield();
        }
        System.out.println(i);
        System.out.println(Thread.currentThread().getName()+"\t..int..end..."+myDate.number);
        System.out.println(Thread.currentThread().getName()+"\t..atomic..end..."+myDate.atomicInteger);
    }

    /**
     *
     * volatile可以保证可见性，即时通知其他线程，主物理内存的值已被修改
     */
    public static void seeOkByVolatile() {

        MyDate myDate = new MyDate();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myDate.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update value "+myDate.number);
        },"AAA").start();

        while (myDate.number==0){
//            System.out.println(Thread.currentThread().getName()+"\t value "+myDate.number);
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over! "+myDate.number );
    }

}
