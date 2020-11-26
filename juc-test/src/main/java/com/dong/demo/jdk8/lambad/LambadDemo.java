package com.dong.demo.jdk8.lambad;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@FunctionalInterface
interface Inteface1{

    int add(int a,int b );

    default int add2(int a,int b){
        return a+b;
    }
    default int add3 (int a,int b){
        return a+b;
    }

}

@FunctionalInterface
interface Inteface2{

    int add(int a,int b );

    default int add2(int a,int b){
        return a+b;
    }


}

@FunctionalInterface
interface Inteface3 extends Inteface1,Inteface2{


    @Override
    default int add2(int a, int b) {
        return 0;
    }
}


public class LambadDemo {


    public static void main(String[] args) {


        lambad1();
    }

    public static void lambad1(){
        Inteface1 inteface1 =(a,b)->a*b;
        Inteface1 inteface11 =(int a,int b)->a*b;
        Inteface1 inteface2 =(a,b)->{
            System.out.println(a*b);
            return a*b;
        };

        System.out.println(inteface1.add(1,2));
        System.out.println(inteface1.add2(1,2));
    }

    public static void sortNum(){
        int [] nums = {33,11,44,99,66,22,11};
        OptionalInt optionalInt = Arrays.stream(nums).min();

        System.out.println(optionalInt.getAsInt());
        System.out.println(IntStream.of(nums).min().getAsInt());
    }

}
