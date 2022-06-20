package com.dong.demo.test.gucutil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore  信号灯或信号量
 * 主要用于俩个目的，一个是用于多个共享资源的互斥使用，另外一个用于并发线程数的控制
 *
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for(int i =1;i<7;i++){//模拟6个汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位,空闲的车位有:"+semaphore.availablePermits());
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t停车3秒离开 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }


    }

}
