package com.dong.demo.jdk8.trick;

import com.alibaba.fastjson.JSON;
import com.dong.demo.jdk8.trick.ifesle.UserEntry;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainOptional {
    public static void main(String[] args) {

        System.out.println(JSON.toJSONString(Lists.newArrayList(1,3,9,3,2).stream().sorted((e1,e2)-> e1-e2).collect(Collectors.toList())));
        UserEntry user = new UserEntry();
        /**
         * name为空不用判断
         */
        int len = Optional.ofNullable(user).map(UserEntry::getName).map(name->name.length()).orElse(0);
        System.out.println(len);
        System.out.println("--------------------");
        testElse();
//        testFaltMap();
//        Integer num = 23;
//        Integer integer = Optional.ofNullable(num).orElse(0);
//        System.out.println(integer);
        Optional<Object> empty = Optional.empty();
        //传入参数不能为null,否则会抛出NullPointerException
        Optional.of(user);
        //创建可为空的Optional，如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional。
        Optional<UserEntry> optional = Optional.ofNullable(user);
        optional.isPresent();//如果存在值，则返回true，否则返回false。
        optional.get();//返回值
    }

    /**
     * orElseThrow
     */
    public static void testException(){
        Integer num = 234;
        Optional.ofNullable(num).orElseThrow(() -> new RuntimeException("数据为空"));

    }
    /**
     * else eLseGet
     */
    public static void testElse(){
        Integer num = 234;
        Integer integer = Optional.ofNullable(num).orElse(111);
        integer = Optional.ofNullable(num).orElseGet(()->{//类似懒加载
            System.out.println("elseGet");
            return 111;
        });
    }

    /**
     * map and flatMap
     */
    public static void testFaltMap(){
        //对于calculate这种函数，使用flatMap的话，返回的结果还是Optional，方便使用
        Optional<Integer> opt = Optional
                .ofNullable(11)
                .flatMap(a -> calculate(a));
        //对于calculate这种函数，如果使用map，返回的结果嵌套了Optional
        Optional<Optional<Integer>> opt2 = Optional
                .ofNullable(11)
                .map(a -> calculate(a));

    }
    public static Optional<Integer> calculate(int input) {
        return Optional.of(input * 2);
    }
    public static Integer calculate2(int input) {
        return input * 2;
    }




}
