package com.dong.demo.jdk8.stream;


import java.util.stream.IntStream;

/**
 * Stream 流编程
 *
 * 内部迭代的优点：
 * 并行，短路，惰性求值
 *
 *
 *
 */
public class SteamDemo {

    public static void main(String[] args) {

        int [] nums = {1,2,3,4,5};

        //map就是中间操作（返回stream的操作）
        //sum终止操作
        int sum = IntStream.of(nums).sum();
         sum = IntStream.of(nums).map(i->i*2).sum();
         sum = IntStream.of(nums).map(SteamDemo::douubleNum).sum();
        System.out.println(sum);

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(SteamDemo::douubleNum);

    }

    public static int douubleNum(int i){
        System.out.println("乘以2");
        return i*2;
    }

}
