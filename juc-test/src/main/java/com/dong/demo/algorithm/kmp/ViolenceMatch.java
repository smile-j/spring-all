package com.dong.demo.algorithm.kmp;

public class ViolenceMatch {

    public static void main(String[] args) {
        System.out.println(violenceMatch("aa bb cc dd ee","dd1"));
    }

    //暴力匹配
    public static int violenceMatch(String str1,String str2){
        int res =-1;

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;

        int i=0,j=0;
        while (i<s1Len&&j<s2Len){
            if(s1[i]==s2[j]){
                i++;
                j++;
            }else {
                i = i - (j-1);
                j=0;
            }
        }
        if (j==s2.length){
            return i-j;
        }
        return res;
    }

}
