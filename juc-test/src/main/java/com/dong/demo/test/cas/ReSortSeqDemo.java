package com.dong.demo.test.cas;


/**
 *指令重排：为了提高性能，编译器和处理器的常常对指令做重排
 * 源代码-->编译器优化的重排-->指令并行的重排-->内存系统的重排-->最终指令
 */
public class ReSortSeqDemo {

    int a =0;
    boolean flag = false;

    public void metho1(){
        a =1;
        flag =true;
    }
    public void metho2(){

        if(flag){
            a = a+5;
            System.out.println("*********retValue: "+a);
        }
    }

}
