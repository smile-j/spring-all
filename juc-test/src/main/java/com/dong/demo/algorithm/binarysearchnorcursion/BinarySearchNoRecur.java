package com.dong.demo.algorithm.binarysearchnorcursion;


/**
 * 二分查找  非递归  升序排列
 *
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {

        int [] arr={1,3,8,10,67,100};
        int index = binarySearch(arr,671);
        System.out.println(index);

    }

    //二分查找 非递归
    public static int  binarySearch(int [] arr,int target){
        int index =-1;
        int left =0,right = arr.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(mid>target){
                right = mid-1;
            }else {
                left = mid +1;
            }
        }
        return index ;
    }
}
