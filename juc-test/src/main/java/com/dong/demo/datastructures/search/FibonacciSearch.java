package com.dong.demo.datastructures.search;

import java.util.Arrays;

/**
 *
 * 斐波那契 查找
 *
 */

public class FibonacciSearch {

    public static int maxSize =20;
    public static void main(String[] args) {
        int [] arr ={1,8,9,100,1000,1006};
        System.out.println("index:"+fibonacciSearch(arr,1006));

    }


    public static int[] fib(){
        int [] f= new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i=2;i<maxSize;i++){
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    /**
     * 使用非递归的方式
     * @param arr
     * @param key  findVal
     * @return
     */
    public static int fibonacciSearch(int [] arr,int key){
        int low = 0;
        int high = arr.length-1;
        int k = 0;
        int mid = 0;
        int f[] = fib();
        while (high>f[k]-1){
            k++;
        }

        int [] temp = Arrays.copyOf(arr,f[k]);
        for(int i = high+1;i<temp.length;i++){
            temp[i]=arr[high];
        }

        while (low<=high){
            mid = low+f[k-1]-1;
            if(key<temp[mid]){
                high = mid-1;
                k--;

            }else if(key>temp[mid]){
                low = mid + 1;
                k-=2;
            }else {
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

}
