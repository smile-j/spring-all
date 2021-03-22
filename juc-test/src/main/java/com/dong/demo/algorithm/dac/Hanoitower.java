package com.dong.demo.algorithm.dac;

/**
 * 汉诺塔 分治算法
 */
public class Hanoitower {

    public static void main(String[] args) {
        hannoiTower(2,'A','B','C');
    }


    public static void hannoiTower(int num,char a,char b,char c ){

        //如果只有一个盘
        if(num==1){
            System.out.println("第1个盘从 "+a+"->"+c);
        }else {
            hannoiTower(num-1,a,c,b);
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            hannoiTower(num-1,b,a,c);

        }

    }

}
