package com.dong.demo.jdk8.trick;

import com.alibaba.fastjson.JSON;
import com.dong.demo.jdk8.trick.ifesle.UserEntry;
import com.dong.demo.test.jvm.User;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainOptional {
    public static void main(String[] args) {
        UserEntry user1 = null;
        UserEntry user2 = new UserEntry().setName("默认");
        //1.创建
        //user可以为空
        Optional<UserEntry> o1 = Optional.ofNullable(user1);
        Optional<UserEntry> o2 = Optional.empty();
        //user不能为空 为空会报异常
//        Optional<UserEntry> o3 = Optional.of(user);
        //user 对象是空的，所以返回了作为默认值的 user2;user不为空 返回user
        UserEntry user = Optional.ofNullable(user1).orElse(user2);
        System.out.println("-------------------------------------");
        /**
         * 2. name为空不用判断
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
     * else 与 orElseGet
     */
    public static void testElse(){
        Integer num = 234;
        Integer integer = Optional.ofNullable(num).orElse(createNewUser("else"));
        System.out.println("testElse:"+integer);
        integer = Optional.ofNullable(num).orElseGet(()->{//类似懒加载
           return createNewUser("orElseGet");
        });
        System.out.println("testElse:"+integer);

    }
    public static Integer createNewUser(String param){
        System.out.println(" 初始化....."+param);
        return 111;
    }


    /**
     * orElseThrow
     */
    public static void testException(){
        Integer num = 234;
        Optional.ofNullable(num).orElseThrow(() -> new RuntimeException("数据为空"));

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

        Optional<Integer> opt23= Optional
                .ofNullable(11)
                .map(a -> calculate2(a));

    }
    public static Optional<Integer> calculate(int input) {
        return Optional.of(input * 2);
    }
    public static Integer calculate2(int input) {
        return input * 2;
    }

    /**
     * filter
     */
    public static void testFilter(){
        UserEntry user = new UserEntry().setName("abcd");
        Optional<UserEntry> result = Optional.ofNullable(user)
                .filter(u -> u.getName() != null && u.getName().contains("@"));
    }

    /**
     * Optional.ifPresent()方法(判读是否为空并返回函数)
     * 这个意思是如果对象非空，则运行函数体
     */
    public static void testifPresent(){
        UserEntry user = new UserEntry().setName("abcd");
        Optional.ofNullable(user).ifPresent(s -> System.out.println("名字：" + user.getName()));

    }


    //-------------------java 9 增强----------------------
    /**
     * or
     * 与 orElse() 和 orElseGet() 类似，它们都在对象为空的时候提供了替代情况
     *  如果对象包含值，则 Lambda 表达式不会执行：
     */
/*    public static void testOr(){
        UserEntry user = null;
        UserEntry result = Optional.ofNullable(user)
                .or( () -> Optional.of(new UserEntry().setName("default"))).get();
    }*/

    /**
     *  ifPresentOrElse() 方法需要两个参数：一个 Consumer 和一个 Runnable。如果对象包含值，会执行 Consumer 的动作，否则运行 Runnable。
     */
/*    public static void testIfPresentOrElse(){
        UserEntry user = null;
        Optional.ofNullable(user).ifPresentOrElse( u -> System.out.println("User is:" + u.getEmail()),
                () -> System.out.println("User not found"));
    }*/
}
