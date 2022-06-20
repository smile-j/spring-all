package com.dong.demo.test.gucutil;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CycliBarrier  招呼神龙
 *
 * CyclicBarrier 基于 Condition 来实现的
 *
 */
public class CycliBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("*****召唤龙珠");
        });

        for (int i=1;i<8;i++){
            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第："+tempInt+"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"...end");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"...end");
    }

}
