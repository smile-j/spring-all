package com.dong.demo.test.threads;

import java.util.concurrent.*;


/**
 *
 * maximumPoolSize:
 *  cpu密集型： cup核心数+1
 *  IO密集型：  1) cpu核心数*2
 *             2) io密集型几大量的io,即大量的阻塞  参考公式： cpu核心数/(1-阻塞系数) 阻塞系数在 0.8-0.9
 *
 * 拒绝策略：
 *  AbortPolicy  报异常
 *  CallerRunsPolicy   “调用运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
 *  DiscardOldestPolicy  :抛入队列中等待最久的任务，然后把当前任务加入队列中再次提交当前任务
 *  DiscardPolicy  直接丢弃任务，不予任何处理也不抛出异常
 */
public class MyThreadPools {

    public static void main(String[] args) {
        System.out.println("线程核心数：" + Runtime.getRuntime().availableProcessors());

        //     Executors   ThreadPoolExecutor;
        ExecutorService executorService = null;
        executorService = Executors.newFixedThreadPool(5);
//        executorService = Executors.newSingleThreadExecutor();
//        executorService = Executors.newCachedThreadPool();
//        executorService = Executors.newScheduledThreadPool(2);
        executorService =new  ThreadPoolExecutor(2,5,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(3)
                ,Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i=0;i<10;i++){
                final int tempInt = i;
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务 start "+tempInt);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务 end "+tempInt);

                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

    public static void threadInit() {

//     Executors   ThreadPoolExecutor;
        ExecutorService executorService = null;
        executorService = Executors.newFixedThreadPool(5);
//        executorService = Executors.newSingleThreadExecutor();
//        executorService = Executors.newCachedThreadPool();
//        executorService = Executors.newScheduledThreadPool(2);

        try {
           for (int i=0;i<10;i++){
               executorService.execute(()->{
                   System.out.println(Thread.currentThread().getName()+"\t 办理业务");
               });
           }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

}
