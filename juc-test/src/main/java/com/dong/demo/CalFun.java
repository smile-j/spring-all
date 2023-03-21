package com.dong.demo;

@FunctionalInterface
public interface CalFun<T, R> {

//    R apply(T t, U u,L l);

    R apply(T t);

}
