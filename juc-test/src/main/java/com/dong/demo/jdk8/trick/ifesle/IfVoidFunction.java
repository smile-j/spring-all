package com.dong.demo.jdk8.trick.ifesle;

import java.util.Map;
import java.util.function.Function;

public class IfVoidFunction<K> {

    private Map<K, Function> mapReturn;


    public void setMap(Map<K, Function> mapReturn) {
        this.mapReturn = mapReturn;
    }


    /**
     * 创建桥接实例
     *
     */
    public static <K>ResIfVoidFunction builder () {
        return new ResIfVoidFunction<>();
    }

    public static class ResIfVoidFunction<K> {
        private Map<K, Function> mapReturn;

        private ResIfVoidFunction() {
        }

        public ResIfVoidFunction<K> buildVoidIf(Map<K, Function> mapReturn) {
            this.mapReturn = mapReturn;
            return this;
        }

        public IfVoidFunction<K> build() {
            IfVoidFunction<K> functionReturn = new IfVoidFunction<>();
            functionReturn.setMap(mapReturn);
            return functionReturn;
        }
    }
}
