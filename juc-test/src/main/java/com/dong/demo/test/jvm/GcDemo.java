package com.dong.demo.test.jvm;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * java -XX:+PrintCommandLineFlags -version
 *
 * 1. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   (DefNew+Tenured)
 *
 * 2. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC   (ParNew+Tenured)
 *  备注：Java HotSpot(TM) 64bit Server VM waring:
 *      Using the ParNew young collector with the Serial old collector is deprecated
 *      and will likely be removed in a future release
 *
 * 3. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC   (PSYoungGEn+ParOldGen)
 *
 *
 * 4
 *
 * 4.1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC   (PSYoungGEn+ParOldGen)
 * 4.2 不加默认UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags    (PSYoungGEn+ParOldGen)
 *
 * 5. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  (par new generation+concurrent mark-sweep)
 *
 * 6.-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 * 7.(理论知道即可，实际已经被优化掉了，没有了)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *
 *
 *  为了学习，一般生产不这么配置
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC -XX:+UseSerialOldGC  (PSYoungGEn+ParOldGen)
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC -XX:+UseConcMarkSweepGC   (par new generation+concurrent mark-sweep)
 *
 */
public class GcDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("......start");
        try {
            String str ="hello world";
            while (true){
                str+=str+new Random().nextInt(777777)+new Random().nextInt(6666666);
                str.intern();

            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        System.out.println("...........end");
    }

}
