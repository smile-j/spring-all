package com.dong.demo.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo2 {

    public static void main(String[] args) {



    }

    /**
     * 流的创建
     */
    public static void  createStrem(){
        List<String> list = new ArrayList<>();

        //从集合创建
        list.stream();
        list.parallelStream();

        //从数组创建
        Arrays.stream(new int[]{1,2,4});

        //创建数字流
        IntStream.of(1,3,5);
        IntStream.rangeClosed(1,10);

        //使random创建一个无限流
        new Random().ints().limit(6);


        //自己产生流
        Random random = new Random();
        Stream.generate(()->{
            return random.nextInt();
        }).limit(10);
    }


}
