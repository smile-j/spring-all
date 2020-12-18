package com.dong.demo.webflux.util;

import com.dong.demo.webflux.exceptions.CheckException;

import java.util.stream.Stream;

public class CheckUtil {

    private static final String[] INVALI_NAMES ={"admin","guanliyuan"};


    public static void checkName(String value){

        Stream.of(INVALI_NAMES).filter(name->name.equalsIgnoreCase(value))
        .findAny().ifPresent(name->{
            throw new CheckException("name",value);
        });

    }

}
