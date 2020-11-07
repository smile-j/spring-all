package com.dong.demo.test.tlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入
 *
 */

class Phone implements Runnable{

    public synchronized void sendMsg(){

        System.out.println(Thread.currentThread().getName()+"\t invoked SendMsg");
        sendEmail();

    }
    public synchronized void sendEmail(){

        System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail");

    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
      get();
    }

    public void get(){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get");
            set();
        }finally {
            lock.unlock();
        }

    }
    public void set(){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set");

        }finally {
            lock.unlock();
        }

    }


}

public class ReenLockDemo {

    public static void main(String[] args) {
        Phone p = new Phone();
        new Thread(()->{
            p.sendMsg();
        },"t1").start();

        new Thread(()->{
            p.sendEmail();
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        new Thread(p,"t3").start();
        new Thread(p,"t4").start();

    }


}
