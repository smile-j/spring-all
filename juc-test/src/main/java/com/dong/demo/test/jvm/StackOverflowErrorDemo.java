package com.dong.demo.test.jvm;

public class StackOverflowErrorDemo {

    public static void main(String[] args) {


        stackOverflowError();
    }

    public static void stackOverflowError(){
        stackOverflowError();//Exception in thread "main" java.lang.StackOverflowError
    }

}
