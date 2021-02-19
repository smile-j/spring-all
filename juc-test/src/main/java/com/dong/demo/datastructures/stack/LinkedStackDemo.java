package com.dong.demo.datastructures.stack;

import java.util.Scanner;

public class LinkedStackDemo {

    public static void main(String[] args) {
        LinkedStack arrayStack = new LinkedStack(5);
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

class LinkedStack{

    private int maxSize ;
    private Node head =null;//表示栈顶
    private int elementCount=0;//栈内元素个数


    public LinkedStack(int maxSize){
        this.maxSize = maxSize;
    }

    public boolean isFull(){
        return maxSize ==elementCount;
    }
    public boolean isEmpty(){
        return elementCount==0;
    }

    public void push(int value){

        if(isFull()){
            System.out.println("栈满！！！");
            return;
        }
        elementCount++;
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    public int pop(){
        if(isEmpty()){
            new RuntimeException("栈空了！！！");
        }
        int value = head.value;
        elementCount--;
        head = head.next;
        return value;
    }

    //从栈顶开始
    public void list(){

        if(isEmpty()){
            System.out.println("栈空了！！！");
            return;
        }
        Node temp = head;
        while (temp!=null){
            System.out.printf("value=%d \n",temp.value);
            temp = temp.next;
        }
    }
}

class Node{
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}