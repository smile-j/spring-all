package com.dong.demo.jdk8.trick.ifesle;

/**
 * 用于执行业务逻辑的接口封装
 * 有返回值执行函数
 * @param <T>
 */
public interface FunctionReturn<T> {

    /**
     * 有返回值的函数
     * @return
     */
    T invokeReturn();
}
