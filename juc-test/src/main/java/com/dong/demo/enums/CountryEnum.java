package com.dong.demo.enums;

import lombok.Getter;

public enum  CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"韩"),FIVE(5,"赵"),SIX(6,"魏");

    CountryEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    @Getter
    private int code;
    @Getter
    private String name;

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] enums =CountryEnum.values();
        for(CountryEnum enums1:enums){
            if(index == enums1.getCode()){
                return enums1;
            }
        }
        return null;
    }
}
