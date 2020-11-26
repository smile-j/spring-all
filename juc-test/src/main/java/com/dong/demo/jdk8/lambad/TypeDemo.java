package com.dong.demo.jdk8.lambad;

@FunctionalInterface
interface IMath{
    int add(int x,int y);
}

@FunctionalInterface
interface IMath2{
    int add(int x,int y);

}


/**
 * 类型推断
 */
public class TypeDemo {

    public static void main(String[] args) {
        //变量类型定义
        IMath lambda = (x,y)->x+y;

        //数组里
        IMath [] lambdas = {(x,y)->x+y};

        //强转
        Object lambad2 = (IMath)(x,y)->x+y;

        //通过返回类型
        IMath createLambda = createLambad();

        //有二义性的时候，通过强转
        TypeDemo typeDemo = new TypeDemo();
        typeDemo.test((IMath) (x, y)->x+y);
        typeDemo.test((IMath2) (x, y)->x+y);

    }

    public void test(IMath math){

    }
    public void test(IMath2 math){

    }

    public static IMath createLambad(){
        return (x,y)->x+y;
    }

}
