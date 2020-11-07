package com.dong.demo.test.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{

    private volatile boolean flag = true;//默认开启
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data = null;
        boolean reValue;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            reValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);

            if(reValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列 "+data+ "成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列 "+data+ "失败");
            }

            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停了，表示Flag=false,生产动作结束");

    }

    public void myConsumer() throws Exception{

        String resutl=null;
        while (flag){
            resutl = blockingQueue.poll(2,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName()+"\t 消费队列 "+resutl+" 成功");
            if(resutl ==null || resutl.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒钟没有取到蛋糕，消费退处");
                System.out.println();
                System.out.println();
                return;
            }
        }


    }

    public void stop() {
        this.flag = false;
    }
}

/**
 *
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 *
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));

        for(int i=0;i<3;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 生产线启动");
                try {
                    myResource.myProd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"prod->"+String.valueOf(i)).start();

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 生产线启动");
                try {
                    myResource.myConsumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"consumer->"+String.valueOf(i)).start();
        }


        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒时间到，大老板main线程叫停，活动结束");
        myResource.stop();

    }

}
