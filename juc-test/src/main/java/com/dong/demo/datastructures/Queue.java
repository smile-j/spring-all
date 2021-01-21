package com.dong.demo.datastructures;

import java.util.Scanner;

/**
 * 队列的实现
 *  可以是数组或者链表：先进先出
 *
 */
public class Queue {

    public static void main(String[] args) {
        ArrayQueueDemo();
        

    }

    public static void ArrayQueueDemo(){
        CycleArrayQueue arrayQueue = new CycleArrayQueue(3);
        char key =' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加元素到队列");
            System.out.println("g(get):从队列获取数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());;
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());;
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出！！！");
    }

}

class ArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front;
    private int rear;
    private int [] arr;//模拟对列 存放数据

    public ArrayQueue(int arrMaxSize){
        this.maxSize = arrMaxSize;
        this.arr = new int [maxSize];
        this.front = -1;//指向队列头前一位置，front是指向队列头的前一个位置
        this.rear = -1;//指向队列尾部，指向队列尾的数据（队列最后的一个数据）
    }

    public boolean isFull(){
        return rear == maxSize-1;
    }

    public boolean isEmpty(){
        return rear == front;
    }
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满了。。。");
            return;
        }
        rear++;
        arr[rear] = n;

    }
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列的头数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

}
class CycleArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front;
    private int rear;
    private int [] arr;//模拟对列 存放数据

    public CycleArrayQueue(int arrMaxSize){
        //队列中有效的数据格式 （rear+maxSize-front）%maxSize
        this.maxSize = arrMaxSize;
        this.arr = new int [maxSize];
        this.front = 0;//指向队列第一个元素，arr[front] 第一个元素
        this.rear = 0;//指向队列最后一个元素的后一个元素
    }

    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    public boolean isEmpty(){
        return rear == front;
    }
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满了。。。");
            return;
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;

    }
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int temp = arr[front];
        front = (front+1)%maxSize;
        return temp;
    }
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i=front;i<front+getSize();i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //获取队列的有效个数
    public int getSize(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列的头数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

}