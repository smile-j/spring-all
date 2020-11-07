package com.dong.demo.test.tlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{

    private int num = 1;//a 1; b 2; c3
    ReentrantLock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){

        lock.lock();
        try {
            while (num!=1){
                c1.await();
            }
            for (int i=1;i<6;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            num =2;
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print10(){

        lock.lock();
        try {
            while (num!=2){
                c2.await();
            }
            for (int i=1;i<11;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            num =3;
            c3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void print15(){

        lock.lock();
        try {
            while (num!=3){
                c3.await();
            }
            for (int i=1;i<16;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            num =1;
            c1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

public class SynAndReentranLockDemo {

    public static void main(String[] args) {


        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i=1;i<11;i++){
                shareData.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i=1;i<11;i++){
                shareData.print10();
            }
        },"B").start();


        new Thread(()->{
            for (int i=1;i<11;i++){
                shareData.print15();
            }
        },"C").start();

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
