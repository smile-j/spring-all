package com.dong.demo.test.block;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 *
 * BlockingQueue
 *
 ** ArrayBlockingQueue 是一个基于数组结构的有界队列，此队列按照FIFO(先进先出)原则对元素进行排序
 ** LinkedBlockingQueue 是一个基于链表结构的有界（大小默认是Intger.Max_value）阻塞队列，此队列按FIFO(先进先出) 排序元素，吞吐量通常高于ArrayBlockingQueu
 * PriorityBlockingQueue 支持优先级排序的无界队列
 * DelayQueue 使用优先级队列实现的延迟无界队列
 ** SynchronousQueue 一个不存储元素的阻塞队列。每个插入必须等到另外一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常高于
 *
 * LinkedTransferQueue 由链表结构组成的无界阻塞队列
 * LinkedBlockingDeque 由链表结构组成的双向阻塞队列
 * 1.队列
 *
 *
 * 2、阻塞队列
 *  应用场景：生产者消费者模式；ProdConsumer_TraditonDemo ProdConsumer_BlockQueueDemo
 *          线程池；
 *          消息中间间
 *
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

       /* 异常方法
       System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.element());//检查是否为空 队首元素
        System.out.println();

        System.out.println(blockingQueue.remove("b"));
        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

       /* 温和性  返回null false
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

  /*    阻塞性
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());*/

        System.out.println(blockingQueue.offer("a",3,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b",3,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c",3,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d",3,TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));

    }
}
