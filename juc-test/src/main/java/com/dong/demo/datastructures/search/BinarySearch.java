package com.dong.demo.datastructures.search;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找:前提是有序的
 *
 * 从小到大
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1000,1000,1000,1234};
        List list = binarySearch2(arr,0,arr.length-1,1000);
//        System.out.println("索引："+ index);
        System.out.println("索引："+ list);
    }

    public static int binarySearch(int [] arr,int left,int right,int findVal){

        if(left>right){
            return -1;
        }

        int mid = (left+right)/2;
        int midVal = arr[mid];


        if(findVal>midVal){
            return binarySearch(arr,mid+1,arr.length-1,findVal);
        }else if(findVal<midVal){
            return binarySearch(arr,left,mid-1 ,findVal);
        }else {
            return mid;
        }

    }

    public static List<Integer> binarySearch2(int [] arr,int left,int right,int findVal){

        if(left>right){
            return new ArrayList<>();
        }


        int mid = (left+right)/2;
        int midVal = arr[mid];


        if(findVal>midVal){
            return binarySearch2(arr,mid+1,arr.length-1,findVal);
        }else if(findVal<midVal){
            return binarySearch2(arr,left,mid-1 ,findVal);
        }else {
            List<Integer> resIndexlist = new ArrayList<>();
            int temp = mid-1;
            //左边扫描
            while (true){
                if(temp<0||arr[temp]!=findVal){//退处
                    break;
                }
                resIndexlist.add(temp);
                temp-=1;
            }
            resIndexlist.add(mid);
            //右边扫描
            temp = mid+1;
            while (true){
                if(temp<0||arr[temp]!=findVal){//退处
                    break;
                }
                resIndexlist.add(temp);
                temp+=1;
            }
            return resIndexlist;
        }

    }

}
