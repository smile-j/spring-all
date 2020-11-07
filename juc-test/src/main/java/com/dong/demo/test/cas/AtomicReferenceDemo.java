package com.dong.demo.test.cas;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@Getter
@ToString
class User{
    String name;
    int age;
}

/**
 * 引用类型的原子类
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User z3 = new User("z3",21);
        User li4 =new User("lisi",22);

        AtomicReference<User> atomicReference = new AtomicReference<User>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());

    }

}
