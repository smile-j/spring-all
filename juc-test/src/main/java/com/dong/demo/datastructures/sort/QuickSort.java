package com.dong.demo.datastructures.sort;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 快速排序
 *快速排序(Qui cksort)是对冒泡排序的一种改进。基本思想是:通过一趟排序
 * 将要排序的数据分割成独立的两部分，其中一部分的所有 数据都比另外-部分
 * 的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个
 * 排序过程可以递归进行，以此达到整个数据变成有序列
 *
 */
public class QuickSort {

    public static void main(String[] args) {

//        int arr[] = {-9,78,0,23,-567,70};

        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int arr [] =new int[80000];
        for(int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*800000);//[0,800000)
        }
        System.out.println("排序前时间："+smf.format(new Date()));
        quickSort(arr,0,arr.length-1);
        System.out.println("排序后时间："+smf.format(new Date()));

//        System.out.println(JSON.toJSONString(arr));
    }

    public static void quickSort(int [] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        int temp = 0;

        while (l<r){

            while (arr[l]<pivot){
                l+=1;
            }
            while (arr[r]>pivot){
                r-=1;
            }
            if(l>=r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //
            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }

        }
        //如果l==r 必须 r--l++,否则为出现栈溢出
        if(l==r){
            l+=1;
            r-=1;
        }
        //向左递归
        if(left<r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right>l){
            quickSort(arr,l,right);
        }

    }


}
