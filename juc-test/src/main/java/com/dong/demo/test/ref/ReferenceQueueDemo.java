package com.dong.demo.test.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;


/**
 * ReferenceQueue
 *
 * java提供了4种引用类型，在垃圾回收的时候，都各有自己的特点
 * ReferenceQueue 用来配合引用工作的，没有ReferenceQueue一样可以运行
 *
 * 创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列
 * 如果程序发现某个虚拟引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动
 * 这相当于是一种通知机制
 *
 *
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("=======================");
        o1=null;
        System.gc();
        Thread.sleep(3);


        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }

}
