package org.dong.starter.vo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dongjunpeng
 * @Description
 * @date 2022/6/22
 */

public class B {

    public B(){
        System.out.println("初始化B......");
    }

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
