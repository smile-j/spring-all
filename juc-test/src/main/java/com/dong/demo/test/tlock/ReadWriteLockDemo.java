package com.dong.demo.test.tlock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{//资源类

    private volatile Map<String,Object> map = new HashMap<>();
//    private Lock  lock = new ReentrantLock();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key,Object value){

        lock.writeLock().lock();
        try {

            System.out.println(Thread.currentThread().getName()+"\t 正在写入"+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成"+key);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }


    }

    public void get(String key){

        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取"+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

    }

    public void clearMap(){
        map.clear();
    }

}

/**
 * 读写锁
 *
 * 读-读共存
 * 读-写 不共存
 * 写-写 不共存
 *
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {


        MyCache myCache = new MyCache();

        for(int i=0;i<5;i++){
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)+"->a").start();
        }
        for(int i=0;i<5;i++){
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)+"->b").start();
        }
    }

}
