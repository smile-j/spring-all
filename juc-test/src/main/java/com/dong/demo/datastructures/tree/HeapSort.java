package com.dong.demo.datastructures.tree;

import java.util.Arrays;

/**
 * 堆排序
 *
 */
public class HeapSort {

    public static void main(String[] args) {
        //升序
        int arr [] = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int [] arr){
        int temp = 0;
        System.out.println("堆排序");
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));//第一次[4, 9, 8, 5, 6]
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+ Arrays.toString(arr));//[9, 6, 8, 5, 4]

        for (int i=arr.length/2-1;i>=0;i--){
                adjustHeap(arr,i,arr.length);
        }

        for (int j= arr.length-1;j>0;j--){
            //
            temp = arr[j];
            arr[j] =arr[0];
            arr[0] =temp;
            adjustHeap(arr,0,j);
        }

    }

    //将一个数组（二叉树），调成一个大顶堆

    /**
     * 功能：完成将以 i 对应的非叶子节点的树调成大顶堆
     *
     * @param arr 待调整的数组
     * @param i  表示非叶子节点的索引
     * @param lenght 表示对多少个元素继续调整，逐渐减少
     */
    public static  void adjustHeap(int [] arr,int i,int lenght){

        int temp = arr[i];
        // k=i*2+1  k是i节点的左子节点
        for (int k=i*2+1;k<lenght;k=k*2+1){
            if(k+1<lenght && arr[k]<arr[k+1]){
                k++;
            }
            if(arr[k]>temp){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }

        arr[i] = temp;
    }
}

