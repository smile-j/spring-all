package com.dong.demo.datastructures.search;


/**
 *
 * 线性查找
 *
 */
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = {1,0,11,-1,34,89};
        int index = seqSearch(arr,11);
        System.out.println("索引："+index);

    }

    public static int seqSearch(int [] arr,int value){
        int index = -1;
        for (int i=0;i<arr.length;i++){
             if(arr[i]==value){
                 return i;
             }
        }
        return index;
    }

}
