package com.dong.demo;

@FunctionalInterface
public interface CalFun2<T,U,L, R> {

    R apply(T t, U u,L l);

//    R apply(T t);

}
