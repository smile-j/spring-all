package com.dong.demo.datastructures.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        int [] piles = {2,4,1,2,7,8};
        System.out.println(maxCoins(piles));

        System.out.println("------------------");
        ReentrantLock lock = new ReentrantLock();

        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+"\t"+"即将获得锁");
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"\t"+"获得锁");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"释放锁");


        }).start();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "即将获得锁");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "\t" + "获得锁");
//                TimeUnit.SECONDS.sleep(20);
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"释放锁");
        }).start();
        System.out.println(".......end.........");

    }

    public static int maxCoins(int[] piles) {

        return 0;
    }

}
