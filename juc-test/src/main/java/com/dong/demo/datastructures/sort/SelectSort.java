package com.dong.demo.datastructures.sort;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {

//        int arr [] ={3,9,-1,10,2};
        int arr [] =new int[80000];
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间："+smf.format(new Date()));
        for(int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*800000);//[0,800000)
        }
        int temp =0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("排序后时间："+smf.format(new Date()));
        System.out.println(JSON.toJSONString(arr));


    }

}
