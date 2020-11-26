package com.dong.demo.jdk8.lambad;

import java.util.function.*;

class Dog{

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public Dog() {
        this.name = "小小";
    }

    public static void bark(Dog dog){
        System.out.println(dog.name+"叫唤");
    }

    public  String eat(String foodName){
        System.out.println(this.name+" 吃了 "+foodName);
        return "狗粮："+foodName;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

/**
 * 方法引用
 *
 */
public class MethodRefrenceDemo {

    public static void main(String[] args) {

        //方法引用
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello world");

        //静态方法的引用
        Dog  dog = new Dog("小白");
        Consumer<Dog> dogConsumer = Dog::bark;
        dogConsumer.accept(dog);

        //非静态方法的引用
//        Function<String,String> function = dog::eat;
        //输入 输出一样
        UnaryOperator<String> function =dog::eat;
//        IntUnaryOperator
        System.out.println(function.apply("牛肉"));

        //使用类名来 非静态方法应用
        BiFunction<Dog,String,String> dogFunction = Dog::eat;
        System.out.println(dogFunction.apply(dog,"骨头"));

        //构造函数的应用
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建对象："+supplier.get());

        //带参的构造函数的方法引用
        Function<String,Dog> function2 = Dog::new;
        System.out.println("创建对象："+function2.apply("旺财"));

    }

}
