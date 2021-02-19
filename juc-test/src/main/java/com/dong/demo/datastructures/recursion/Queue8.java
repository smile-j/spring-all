package com.dong.demo.datastructures.recursion;


/**
 * 八皇后问题，是一个古老而著名的问题，是
 * 回溯算法的典型案例。该间题是国际西洋棋
 * 棋手马克斯.贝瑟尔于1848年提出:在8X8格
 * 的国际象棋上摆放八个皇后，使其不能互相
 * 攻击，即:任意两个皇后都不能处于同
 * 一行、同一列或同一斜线上，问有多少
 * 种摆法。[92]
 *
 */
public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max =8;
    //定义数组array,保存皇后放置的位置，比如arr=[0,4,7,5,2,6,1,3];
    int [] array = new int[max];
    static int count =0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("count:"+count);
    }

    /**
     * 放置第n个皇后
     * @param n
     */
    private void check(int n){
//        System.out.println("........n......"+n);
        if(n==max ){//
            print();
            return;
        }
        //以此放入
        for(int i=0;i<max;i++){
            //先把当前这个皇后n,放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){//不冲突
                //接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n] = i;
            // 即将第n个皇后，放置在本行得后移一个位置




        }
    }

    /**
     * 当放置第n个，去检查该皇后是否和前面的已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        for(int i=0;i<n;i++){
            //1.array[i]==array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n-i)==Math.abs(array[n]-array[i] 表示判断第n个皇后和第i个皇后是否在同一斜线
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //打印
    private void print(){
        count++;
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }


}
