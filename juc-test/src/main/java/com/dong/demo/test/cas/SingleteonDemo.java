package com.dong.demo.test.cas;

/**
 * 单例模式
 */
public class SingleteonDemo {


    private static volatile SingleteonDemo singleteonDemo;

    private SingleteonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 构造方法");
    }

    public static SingleteonDemo getInstance(){
        if(singleteonDemo==null){
            synchronized (SingleteonDemo.class){
                if(singleteonDemo==null){
                    singleteonDemo = new SingleteonDemo();
                }
            }
        }

        return singleteonDemo;
    }

    public static void main(String[] args) {

    }

}
