package com.dong.demo.jdk8.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Stream的机制
 *无状态
 *  1.所有的操作是链式调用，一个元素只迭代一次
 *  2.每一个中间操作返回一个新的流
 * 3.有状态操作会把无状态操作阶段，单独处理
 * 4.并行环境下，有状态的中间操作不一定能并行操作
 * 5.parallel/squetial 这俩个操作也是中间操作（也是返回stream）
 *  但是他们不创建流，他们只修改head的并行标志
 *
 */

public class RunStrem {


    public static void main(String[] args) {

        Random random = new Random();
        Stream<Integer>stream = Stream.generate(()->random.nextInt())
                .limit(500)
                //第一个无状态操作
                .peek(s-> print("peek:"+s))
                //第二个无状态操作
                .filter(s->{
                    print("filter:"+s);
                    return s >1000;
                })
                .sorted((i1,i2)->{
                    print("排序："+i1+","+i2);
                    return i1.compareTo(i2);
                })
                .peek(s-> print("peek2:"+s))
                .parallel();

         //终止操作
        stream.count();

    }

    public static void print(String str){
        System.out.println(Thread.currentThread().getName()+">"+str);
    }
}
