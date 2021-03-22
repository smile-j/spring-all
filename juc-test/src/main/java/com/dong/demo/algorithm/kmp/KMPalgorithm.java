package com.dong.demo.algorithm.kmp;

import java.util.Arrays;

public class KMPalgorithm {

    public static void main(String[] args) {

        String str1="BBC ABCDAB ABCDABCDABDE";
        String str2="ABCDABD";

        int [] next = kmpNext(str2);
//        System.out.println("next="+ Arrays.toString(next));
        System.out.println("index="+ kMPalgorithm(str1,str2,next));

    }

    //获取一个字符串的部分匹配值表
    public static int [] kmpNext(String dest){
        int [] next = new int[dest.length()];
        next[0] = 0;

        for (int i=1,j=0;i<dest.length();i++){
            //kmp 核心
            while (j>0&&dest.charAt(i)!=dest.charAt(j)){
                j = next[j-1];
            }

            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }

        return next;
    }

    /**
     *
     * @param str1  原串
     * @param target 字串
     * @param next
     * @return
     */
    public static int kMPalgorithm(String str1,String target,int [] next){

        for (int i=0,j=0;i<str1.length();i++){

            while (j>0&&str1.charAt(i)!=target.charAt(j)){
                j = next[j-1];
            }

            if(str1.charAt(i)==target.charAt(j)){
                j++;
            }
            if (j==target.length()){
                return i-target.length()+1;
            }

        }

        return -1;
    }

}
