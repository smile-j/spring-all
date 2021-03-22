package com.dong.demo.test;

public class TestStr {


    public static void main(String[] args) {

        System.out.println(getNum(7));

       /* String s1= "aa";
        String s2= new String("aa");
        String s3= new String("aa");

        System.out.println(s1==s2.intern());
        System.out.println(s1==s2);

        System.out.println();
        String str1 = new StringBuilder ("58"). append("tongcheng").toString();
        System.out.println();
        System.out. println(str1.intern());
        System.out.println(str1 == str1.intern());*/


//        System.out.println();
//        String str2 = new StringBuilder("ja").append("va").toString();
//        System.out.println();
//        System.out.println(str2.intern());
//        System.out.println(str2==str2.intern());

    }


    public static int getNum(int num){
       int n =1;
       while (n<=num){
           n=n<<1;
       }
       return n;
    }

}
