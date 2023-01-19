package com.dong.demo.jdk8.trick.ifesle;

/**
 * 带有返回值的if函数实例
 */

import cn.hutool.core.util.ObjectUtil;
import com.sun.istack.internal.NotNull;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @Description:  带有返回值的if函数实例
 */
public class IfFunctionReturn<K, T> {

    private Map<K, FunctionReturn<T>> mapReturn;

    /**
     * 如果不为null，则为该值；
     * 否则为false。如果为null，则表示不存在任何值
     */
    private T result;


    public void setMap(Map<K, FunctionReturn<T>> mapReturn) {
        this.mapReturn = mapReturn;
    }

    public IfFunctionReturn() {
    }

    /**
     * 添加条件 有返回值函数
     *
     * @param key            需要验证的条件（key）
     * @param functionReturn 要执行的方法
     * @return this.
     */
    public IfFunctionReturn<K, T> addReturn(K key, FunctionReturn<T> functionReturn) {
        this.mapReturn.put(key, functionReturn);
        return this;
    }

    /**
     * 批量添加条件 有返回值函数
     *
     * @param key            需要验证的条件（key）
     * @param functionReturn 要执行的方法
     * @return this.
     */
    @SafeVarargs
    public final IfFunctionReturn<K, T> addReturnAll(FunctionReturn<T> functionReturn, K... key) {
        for (K element : key) {
            if (ObjectUtil.isNotEmpty(element)) {
                this.mapReturn.put(element, functionReturn);
            }
        }
        return this;
    }

    /**
     * 确定key是否存在，如果存在，则执行value中的函数。
     * <p>
     * 函数有返回值
     * <p>
     * 若key为对象类型 则需重写 equal方法和hashcode方法
     * key值和map中的key值必须一致
     *
     * @param key the key need to verify
     */

    public IfFunctionReturn<K, T> doIfEqualReturn(@NotNull K key) {
        if (this.mapReturn.containsKey(key)) {
            this.result = mapReturn.get(key).invokeReturn();
            return this;
        }
        return this;
    }

    /**
     * 确定key是否存在，如果存在，则执行value中的函数。若不存在执行默认函数
     * <p>
     * 函数无返回值 增加默认执行函数 若传入条件皆不符合 则执行默认函数
     * <p>
     * 若key为对象类型 则需重写 equal方法和hashcode方法
     * key值和map中的key值必须一致
     *
     * @param key the key need to verify 条件值
     */
    public IfFunctionReturn<K, T> doIfEqualReturn(@NotNull K key, @NotNull FunctionReturn<T> defaultFunction) {
        boolean doesItContain = this.mapReturn.containsKey(key);
        if (doesItContain) {
            this.result = mapReturn.get(key).invokeReturn();
            return this;
        }
        this.result = defaultFunction.invokeReturn();
        return this;
    }

    /**
     * 比较对象类型是否一致 若一致则执行函数
     * <p>
     * 注意:此方法仅支持 同一个classloader加载两个类使用
     * <p>
     * 函数无返回值
     *
     * @param key the key need to verify 条件值
     */
    public IfFunctionReturn<K, T> doIfInstance(@NotNull K key) {
        mapReturn.forEach((setKey, value) -> {
            if (setKey.equals(key.getClass())) {
                this.result = value.invokeReturn();
            }
        });
        return this;
    }

    /**
     * 比较对象类型是否一致 若一致则执行函数 若不一致 执行默认函数
     * <p>
     * 注意:此方法仅支持 同一个classloader加载两个类使用
     * <p>
     * 函数无返回值 增加默认执行函数 若传入条件皆不符合 则执行默认函数
     *
     * @param key the key need to verify 条件值
     */
    public IfFunctionReturn<K, T> doIfInstance(@NotNull K key, @NotNull FunctionReturn<T> defaultFunction) {

        boolean execution = true;
        for (Map.Entry<K, FunctionReturn<T>> entry : mapReturn.entrySet()) {
            if (entry.getKey().equals(key.getClass())) {
                this.result = entry.getValue().invokeReturn();
                execution = false;
            }
        }
        if (execution) {
            this.result = defaultFunction.invokeReturn();
        }
        return this;
    }

    /**
     * 获取当前函数 返回值
     * <p>
     * 警告: 返回值可能为 null
     *
     * @return 返回对象
     */
    public T get() {
        if (result == null) {
            throw new NoSuchElementException("返回值对象为空!");
        }
        return result;
    }

    public T defaultValue(T other) {
        return result != null ? result : other;
    }
    /**
     * 执行完毕之后自动刷新 map 防止出现数据冲突
     */
    public IfFunctionReturn<K, T> refresh() {
        this.mapReturn.clear();
        return this;
    }

    /**
     * 创建桥接实例
     *
     * @param <K>条件类型泛型
     * @param <T>返回值类型泛型
     */
    public static <K, T> ResIfFunctionReturn<K, T> builder() {
        return new ResIfFunctionReturn<>();
    }

    public static class ResIfFunctionReturn<K, T> {
        private Map<K, FunctionReturn<T>> mapReturn;

        private ResIfFunctionReturn() {
        }

        public ResIfFunctionReturn<K, T> buildReturnIf(Map<K, FunctionReturn<T>> mapReturn) {
            this.mapReturn = mapReturn;
            return this;
        }

        public IfFunctionReturn<K, T> build() {
            IfFunctionReturn<K, T> functionReturn = new IfFunctionReturn<>();
            functionReturn.setMap(mapReturn);
            return functionReturn;
        }
    }
}

