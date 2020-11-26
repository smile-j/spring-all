package com.dong.demo.jdk8.stream;


import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *终止操作：
 *
 *  非短路操作：
 *      forEach/forEachOrdered(和并行流相关)
 *      collect/toArray
 *      reduce
 *      min/Max/count
 *
 *  短路操作：
 *      findFirst/findAny
 *      allMatch/anyMatch
 *
 *
 */
public class StreamDemo4 {

    public static void main(String[] args) {

        String str ="my name is hello";

        //使用并行流
        str.chars().parallel().forEach(i->System.out.print((char)i));
        System.out.println("");
        //forEachOrdered 保证有序
        str.chars().parallel().forEachOrdered(i->System.out.print((char)i));
        System.out.println("");

        //收集list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        //使用reduce拼接字符串
        Optional<String> letters = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(letters.orElse(""));

        //带初始化值的reduce
        String reduce = Stream.of(str.split(" ")).reduce("",(s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce);

        //计算所有的单词总长度
        int len = Stream.of(str.split(" ")).map(i->i.length())
                .reduce(0,(s1, s2) -> s1  + s2);
        System.out.println(len);

        //max
        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());

        System.out.println("---------短路操作------");

        //findFirst
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());

        boolean b = new Random().ints().anyMatch(i -> {
            System.out.println(i);
            return i > 10;
        });


    }

}
