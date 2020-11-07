package com.dong.demo.test.gucutil;

import com.dong.demo.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 计数器
 *百米比赛
 *
 */
public class CountDownLatchDemo {



    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i=1;i<7;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国,被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getName()).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t秦帝国,统一");

        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getName());
        System.out.println(CountryEnum.ONE.getCode());

    }

    public static void closeDoor() throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i=1;i<7;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t完事,走人");
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t关灯,走人");
    }

}
