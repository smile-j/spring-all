package com.dong.demo.test.jvm;


/**
 *
 * gc roots
 *
 * 1)虚拟机栈(栈帧中的局部变量区，也叫局部变量表)中引用的对象;
 * 2)方法区中类静态属性引用的对象;
 * 3)方法区中常量引用的对象;
 * 4)本地方法栈中JNI引用的对象。
 *
 */
public class GcRootDemo {

    private byte [] byteArray = new byte[100*1024*1024];

//    private static GcRootDemo2 t2;
//    private static GcRootDemo3 t3 = new GcRootDemo3();

    public static void m1(){
        GcRootDemo t1 = new GcRootDemo();
        System.gc();
        System.out.println("第一次GC完成");

    }

    public static void main(String[] args) {
        m1();
    }
}
