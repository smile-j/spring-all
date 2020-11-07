package com.dong.demo.calculation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 排序算法
 */
public class SortMain {



    public static void main(String [] args){
        int [] nums  = {21,18,76,11,55,81,97,65};
        System.out.println("排序前："+ JSONObject.toJSONString(nums));
        Long startTime = System.currentTimeMillis();
        shellSort(nums);
        System.out.println("耗时："+(System.currentTimeMillis()-startTime));
        System.out.println("排序后："+ JSONObject.toJSONString(nums));
    }

    /*
    冒泡
     */
    public static void bubbleSort(int [] arrays){

        for (int i=0;i<arrays.length;i++){
            for(int j=i+1;j<arrays.length;j++){
                if(arrays[i]>arrays[j]){
                    int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j]=temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * @param arrays
     */
    public static void selectionSort(int [] arrays){

        for (int i=0;i<arrays.length;i++){
            int minIndex =i;
            for(int j=i+1;j<arrays.length;j++){
                if(arrays[minIndex]>arrays[j]){
                    minIndex = j;
                }
            }
            if(i!=minIndex){
                int temp = arrays[i];
                arrays[i] = arrays[minIndex];
                arrays[minIndex] = temp;
            }
        }

    }

    /**
     * 插入排序
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     * @param arrays
     */
    public static void insertionSort(int [] arrays){

        for (int i=0;i<arrays.length;i++){
            for(int j =0;j<i+1;j++){
                if(arrays[i]<arrays[j]){
                   int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }

    }

    /**
     * 希尔排序
     *
     *
     * @param array
     */
    public static void shellSort(int [] array){

        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }


    }

    /**
     * 二分法排序
     * @param array
     */
    public static void dichotomizingsort(int [] array){
        for(int i=1;i<array.length;i++){
            int mid = i/2;
            int left =0,end = i;
            while (mid>0 && left<end){
//                if(array[mid]){
//                    break;
//                }
            }
        }
    }

}
