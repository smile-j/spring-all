package org.dong.starter.vo;

import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2022/6/22
 */
public class A {

    public A(){
        System.out.println("初始化A......");
    }

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
