package com.dong.demo.jdk8.trick;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * if esle 或者策略 通过枚举解决
 */
@Getter
@AllArgsConstructor
public enum  TypeEnun {

    TYPE_ONE("1",(e->{
        Assert.isTrue(e.size() == 1,"长度大于1");}),(e->{return e.length();}))
    ,TYPE_TWO("2",(e->{}),(e->{return 1;}));
    /**
     * 类型
     */
    private final String appKey;

    /**
     * 校验
     */
    private final Consumer<List<String>> checkInfo;

    /**
     * 获取加密信息
     */
    private final Function<String, Integer> getInfo;

}
