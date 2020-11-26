package com.dong.demo.test.jvm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int age;
    private String name;
    private String add;


    @Override
    protected void finalize() throws Throwable {

//        System.out.println("---------------------->"+this.getName());

    }
}
