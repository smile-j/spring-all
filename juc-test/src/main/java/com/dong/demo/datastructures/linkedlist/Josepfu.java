package com.dong.demo.datastructures.linkedlist;

import lombok.Data;

/**
 * Josepfu问题
 *  环形链表
 *
 */
public class Josepfu {

    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        System.out.println("-------------");
        circleSingleLinkedList.show();
        circleSingleLinkedList.count(1,2,5);//2,4,1,5,3


    }

}

class CircleSingleLinkedList{

    private Boy fisrt = new Boy(-1);

    public void add(int nums){
        if(nums<1){
            System.out.println("no 不正确.......");
        }
        Boy cur = null;
        for (int i=1;i<=nums;i++){
            Boy boy = new Boy(i);
            if(i==1){
                fisrt = boy;
                fisrt.setNext(fisrt);
                cur = fisrt;
            }else {
                boy.setNext(cur.getNext());
                cur.setNext(boy);
                cur = boy;
            }
        }

    }
    public void show(){
        if(fisrt==null){
            System.out.println("链表为空！！！！");
        }
        Boy cur = fisrt;
        while (true){
            System.out.printf("小孩的编号：%d \n",cur.getNo());
            if(cur.getNext()==fisrt){
                break;
            }
            cur = cur.getNext();
        }



    }

    /**
     *
     * @param startNo 从几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void count(int startNo,int countNum,int nums){

        if(fisrt ==null||startNo<1||startNo>nums){
            System.out.println("参数输入错误！！！");
            return;
        }
        Boy helper = fisrt;
        while (true){
            if(helper.getNext()==fisrt){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first 和helper 移动 k-1次
        for (int j=0;j<startNo-1;j++){
            fisrt = fisrt.getNext();
            helper = helper.getNext();
        }
        //开始报数  first 和helper 指针同时移动m-1次，然后出圈
        while (true){
            if(helper==fisrt){
                break;
            }
            for (int j=0;j<countNum-1;j++){
                fisrt = fisrt.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n",fisrt.getNo());
            helper.setNext(fisrt.getNext());
            fisrt = helper.getNext();
        }

        System.out.printf("最后小孩%d出圈\n",fisrt.getNo());

    }

}

class Boy{

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
