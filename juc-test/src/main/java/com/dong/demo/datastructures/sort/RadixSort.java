package com.dong.demo.datastructures.sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序：
 *  将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，
 *  从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 *
 */
public class RadixSort {

    public static void main(String[] args) {

//        int arr[] = { 53,3,542,748,14,214 }; //

        //测试快排的执行速度
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        radixSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

//        System.out.println("基数排序后=" + Arrays.toString(arr));

    }

    public static void radixSort(int arr[]){

        int [][] bucket = new int[10][arr.length];
        int [] bucketElementCounts = new int[10];
        int maxElement = arr[0];

        for(int i=1;i<arr.length;i++){
            if(arr[i] > maxElement){
                maxElement = arr[i];
            }
        }

        int length = String.valueOf(maxElement).length();

        for(int i=0;i<length;i++){

            int s = (int) Math.pow(10,i);

            for (int j=0;j<arr.length;j++){
                int digitOfElement = arr[j]/s%10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            for (int k=0;k<bucketElementCounts.length;k++){
                if(bucketElementCounts[k]!=0){
                    for(int l=0;l<bucketElementCounts[k];l++){
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] =0;
            }

        }



    }

}
