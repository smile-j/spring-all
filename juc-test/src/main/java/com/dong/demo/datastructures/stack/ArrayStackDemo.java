package com.dong.demo.datastructures.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        String key = null;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("push:添加元素到队列");
            System.out.println("pop:从队列获取数据");
            System.out.println("请输入你的选择：");
            key = scanner.next();//接收一个字符
            switch (key) {
                case "s":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        ;
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }

    }

}

 class ArrayStack{

    private int maxSize ;
    private int[] stack;
    private int top=-1;//表示栈顶

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        return top==maxSize-1;
    }
    public boolean isEmpty(){
        return top ==-1;
    }

    public void push(int value){

        if(isFull()){
            System.out.println("栈满！！！");
            return;
        }
        top++;
        stack[top]=value;
    }

    public int pop(){
        if(isEmpty()){
            new RuntimeException("栈空了！！！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //从栈顶开始
    public void list(){

        if(isEmpty()){
            System.out.println("栈空了！！！");
        }
        int count = top;
        while (count!=-1){
            System.out.printf("stack[%d]=%d \n",count,stack[count]);
            count--;
        }
    }
}
