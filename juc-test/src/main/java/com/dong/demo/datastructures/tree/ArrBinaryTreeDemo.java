package com.dong.demo.datastructures.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 以数组的方式存储二叉树
 *
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {

        int [] arr ={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder();//1,2,4,5,3,6,7
        arrBinaryTree.infixOrder();//4,2,1,5,6,3,7
//        arrBinaryTree.postOrder();//4,5,2,6,7,3,1



    }

}

@Data
@AllArgsConstructor
class ArrBinaryTree{
    private int [] arr;

    public void preOrder(){
        preOrder(0);
    }
    public void infixOrder(){
        infixOrder(0);
    }

    public void postOrder(){
        postOrder(0);
    }
    //前序遍历
    public void preOrder(int index){
        if (arr ==null || arr.length ==0){
            System.out.println("数组为空，不能遍历！");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index*2+1)<arr.length){
            preOrder(index*2+1);
        }
        //向右递归遍历
        if ((index*2+2)<arr.length){
            preOrder(index*2+2);
        }
    }


    //中序遍历
    public void infixOrder(int index) {
        if (arr ==null || arr.length ==0){
            System.out.println("数组为空，不能遍历！");
        }

        //向左递归遍历
        if ((index*2+1)<arr.length){
            infixOrder(index*2+1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向右递归遍历
        if ((index*2+2)<arr.length){
            infixOrder(index*2+2);
        }
    }
    //后序遍历
    public void postOrder(int index) {
        if (arr ==null || arr.length ==0){
            System.out.println("数组为空，不能遍历！");
        }

        //向左递归遍历
        if ((index*2+1)<arr.length){
            postOrder(index*2+1);
        }
        //向右递归遍历
        if ((index*2+2)<arr.length){
            postOrder(index*2+2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
    }
}