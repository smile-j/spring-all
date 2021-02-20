package com.dong.demo.datastructures.search;

/**
 *
 * 插值查找：
 *
 * 插值查找算法类似于二分查找，不同的是插值查找每次从自适应mid处开始查找。
 * 将折半查找中的求mid 索引的公式 , low 表示左边索引left, high表示右边索引right.  key 就是前面我们讲的  findVal
 *
 *
 *
 *
 * int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low])  ;
 * 插值索引 对应前面的代码公式：int mid = left + (right – left) * (findVal – arr[left]) / (arr[right] – arr[left])
        * 举例说明插值查找算法 1-100 的数组
 *
 */
public class InsertValueSearch {

    public static void main(String[] args) {

        int [] arr =new  int[100];

        for (int i=0;i<100;i++){
            arr[i]=i+1;
        }
        int index = insertValueSearch(arr,0,arr.length-1,11);
        System.out.println("索引值："+index);

    }

    public static int insertValueSearch(int []arr,int left,int right,int findVal){
        if(left>right||findVal<arr[0]||findVal>arr[arr.length-1]){
            return -1;
        }

        int mid = left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        if(arr[mid]>findVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else if(arr[mid]<findVal){
            return insertValueSearch(arr,mid+1,right,findVal);
        }else {
            return mid;
        }
    }
}
