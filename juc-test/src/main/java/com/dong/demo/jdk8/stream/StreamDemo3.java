package com.dong.demo.jdk8.stream;


import java.util.Random;
import java.util.stream.Stream;

/**
 *
 * Stream 中间操作
 *
 *  无状态
 *      map/mapToXxx
 *      flatMap/flatMapToXxx
 *      filter
 *      peek
 *      unordered
 *
 *  有状态
 *      distinct
 *      sorted
 *      limit/skip
 *
 *
 */
public class StreamDemo3 {

    public static void main(String[] args) {

        String str = "my name is helloworld!!!";

        Stream.of(str.split(" ")).map(s -> s.length()).forEach(System.out::println);
        Stream.of(str.split(" ")).filter(s->s.length()>2).map(s -> s.length()).forEach(System.out::println);

        //flatMap A->B属性（是个集合），最终得到所有A元素的里面的所有B属性的集合
        //intStream/longStream 并不是Stream的字类，所以需要进行装箱boxed
//        Stream.of(str.split(" ")).flatMap(s->s.chars().boxed()).forEach(System.out::println);
        Stream.of(str.split(" ")).flatMap(s->s.chars().boxed()).forEach(i->System.out.println((char)i.intValue()));

        System.out.println("---------------------------------");
        //peek用于dubugger 是个中间操作，和forEach   是终止操作
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

        //limit 使用，主要用于无限流
        new Random().ints().limit(10).forEach(System.out::println);

    }







}
