package com.dong.demo.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * 插入排序：把n个待排序的元素看成为一个有序的和一个无序表，开始时有序表只包含一个元素，无序表中包含有n-1个元素
 *
 */
public class InsertSort {

    public static void main(String[] args) {
        int arr [] ={3,9,-1,10,2};
//        int arr [] =new int[80000];
//        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        for(int i=0;i<arr.length;i++){
//            arr[i] = (int) (Math.random()*800000);//[0,800000)
//        }
//        System.out.println("排序前时间："+smf.format(new Date()));
        insertionSort(arr);

        System.out.println("数组："+Arrays.toString(arr));
    }

    private static void insertionSort(int[] arr) {
        for(int i=1;i<arr.length;i++){
            int insertVal = arr[i];
            int insertIndex = i-1;
            while (insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if(insertIndex+1!=i){
                arr[insertIndex+1]= insertVal;
            }
            System.out.println(i+"轮数组："+ Arrays.toString(arr));
        }
    }
}
