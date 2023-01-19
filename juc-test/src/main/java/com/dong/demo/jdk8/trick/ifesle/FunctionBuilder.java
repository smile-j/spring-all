package com.dong.demo.jdk8.trick.ifesle;

import java.util.Map;
import java.util.function.Function;

/**
 * 构建if函数的创建工具类
 */
public class FunctionBuilder {
    /**
     * 创建无返回值if函数
     *
     * @param map Map<条件,执行函数>
     * @param <K> 条件参数类型
     * @return if函数实例
     * @see Map
     */
    public static <K> IfVoidFunction<K> voidIf(Map<K, Function> map) {
        return IfVoidFunction.<K>builder().buildVoidIf(map).build();
    }

    public static <K, T> IfFunctionReturn<K, T> returnIf(Map<K, FunctionReturn<T>> returnMap) {
        return IfFunctionReturn.<K, T>builder().buildReturnIf(returnMap).build();
    }

}
