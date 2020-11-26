package com.dong.demo.test.jvm;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * JVM参数: -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * Metaspace 是方法区在HotSpot种实现的，它与持久代最大的区别在于：Metaspace 并不在虚拟机内存中而是使用本地内存
 * 也即在java8中，classe metadata(thi virtual machines internal persentation of java class),被存储在叫做
 * Metaspace的native memory
 *
 * 永久代（Java8后被原空间Metaspace去代了）存放了以下信息：
 *
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 *
 *
 *
 *
 */
public class MetadataOOMDemo {

    static class OOMTest{

    }

    public static void main(final String[] args) {

        int i =0;
        try {

            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invoke(o,args);
                    }
                });

                enhancer.create();
            }

        }catch (Throwable e){
            System.out.println("=================="+i);
            e.printStackTrace();//java.lang.OutOfMemoryError: Metaspace
        }finally {
        }


    }

}
