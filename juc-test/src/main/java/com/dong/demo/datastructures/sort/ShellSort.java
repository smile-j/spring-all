package com.dong.demo.datastructures.sort;

import java.util.Arrays;

/**
 * 希尔排序  缩小增量排序
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序;
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰
 * 被分成一组，算法便终止
 */
public class ShellSort {

    public static void main(String[] args) {

        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前的：" + Arrays.toString(arr));
        shellSort2(arr);
        System.out.println("排序后的：" + Arrays.toString(arr));

    }

    /**
     * 希尔排序时，对有序序列在插入时采用交换法
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮后的数组：" + Arrays.toString(arr));
        }

       /* for(int i=5;i<arr.length;i++){
            for (int j=i-5;j>=0;j-=5){
                if(arr[j]>arr[j+5]){
                    temp =  arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("1轮："+Arrays.toString(arr));
        for(int i=2;i<arr.length;i++){
            for (int j=i-2;j>=0;j-=2){
                if(arr[j]>arr[j+2]){
                    temp =  arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("2轮："+Arrays.toString(arr));
        for(int i=1;i<arr.length;i++){
            for (int j=i-1;j>=0;j-=1){
                if(arr[j]>arr[j+1]){
                    temp =  arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("3轮："+Arrays.toString(arr));*/

    }

    /**
     * 希尔排序时，对有序序列在插入时采用移动发
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for(int i= gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j-gap]){
                    while (j-gap>=0&&temp<arr[j-gap]){
                        //移动
                        arr[j]=arr[j-gap];
                        j -=gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮后的数组：" + Arrays.toString(arr));
        }
    }
}
