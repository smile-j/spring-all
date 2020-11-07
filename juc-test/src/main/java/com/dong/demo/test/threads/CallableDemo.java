package com.dong.demo.test.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" callable");
        return 1024;
    }
}

public class CallableDemo {


    public static void main(String[] args) {

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread2());
        Thread t1 = new Thread(futureTask,"AA");
         new Thread(futureTask,"BB").start();
        t1.start();
        try {
            while (!futureTask.isDone()){

            }
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("..........end");

    }


}
