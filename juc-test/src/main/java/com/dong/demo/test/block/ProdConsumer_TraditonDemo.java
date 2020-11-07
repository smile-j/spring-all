package com.dong.demo.test.block;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * syn wait notify
 *
 * lock await singal
 */

class ShareData{

    private int num =0 ;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{

        lock.lock();
       try {
           while (num!=0){

               condition.await();
           }
           num++;
           System.out.println(Thread.currentThread().getName()+"\t"+num);
           //通知唤醒
           condition.signalAll();
       }finally {
           lock.unlock();
       }
    }

    public void decrement ()throws Exception{
        lock.lock();
        try {
            while (num==0){

                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //通知唤醒
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }


}

public class ProdConsumer_TraditonDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        for(int i=1;i<6;i++){
            new Thread(()->{
               for(int j=0;j<5;j++){
                   try {
                       shareData.increment();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
            },"a"+String.valueOf(i)).start();
        }

        for(int i=1;i<6;i++){
            new Thread(()->{
               for (int j=0;j<5;j++){
                   try {
                       shareData.decrement();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
            },"b"+String.valueOf(i)).start();
        }
    }
}
