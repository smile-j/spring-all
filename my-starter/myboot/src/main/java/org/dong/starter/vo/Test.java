package org.dong.starter.vo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dongjunpeng
 * @Description
 * @date 2022/6/22
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("appliactionContext.xml");
        A bean = context.getBean(A.class);
        B b = context.getBean("b",B.class);
    }

}
