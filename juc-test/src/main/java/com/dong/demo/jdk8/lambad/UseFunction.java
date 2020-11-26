package com.dong.demo.jdk8.lambad;

import java.text.DecimalFormat;
import java.util.function.*;

@FunctionalInterface
interface IMoneyFormart{
    String format(int i);
}


/**
 *
 *
 * 函数式编程 lambda
 *  接口               输入参数      返回类型   说明
 * Predicate<T>          T         boolean     断言
 * Consumer<T>          T           /         消费一个数据
 * Funtion<T><R>        T           R       输入T输出R的函数
 * Supplier<T>          /           T       提供一个数据
 * UnaryOperator<T>    T           T        一元函数(输出输入)
 * BiFunction<T,U,R>  (T,U)         T       2个输入的函数
 * BinaryOperator<T> (T,T)          T       二元参数(输出输入类型相同)
 *
 */
public class UseFunction {

    private final int money;

    public UseFunction(int money){
        this.money = money;
    }

    public void printMoney(Function<Integer,String> function){
        System.out.println("我的存款："+function.apply(this.money));
    }

    public void printMoney2(IMoneyFormart formart){
        System.out.println("我的存款："+formart.format(this.money));
    }

    public static void main(String[] args) {

        //断言函数
//        IntPredicate  基本数据类型的断言
        Predicate<Integer> predicate = i->i>0;
        System.out.println(predicate.test(2));
        System.out.println(predicate.test(-2));

        //消费函数接口
//        IntConsumer
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");

     /*   UseFunction myMoney = new UseFunction(12344566);
        myMoney.printMoney((i)->new DecimalFormat("#,####").format(i));

        Function<Integer,String> moneyFormat = (i)->new DecimalFormat("#,####").format(i);
        myMoney.printMoney(moneyFormat.andThen(s->"人民币"+s));*/

        System.out.println("----------------------------------------------------------------");
        Consumer<String> consumer1 = s -> System.out.println("consumer1:"+s);
        Consumer<String> consumer2 = s -> System.out.println("consumer2:"+s);
        consumer1.andThen(consumer2).accept("123");


    }

}
