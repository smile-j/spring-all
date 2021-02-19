package com.dong.demo.entity;

import com.dong.demo.enums.GenderEnum;
import com.dong.demo.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Student {

    private String name;
    private Integer age;
    private Grade grade;
    private GenderEnum gender;

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("11",null);
        map.put("22","22");
        System.out.println(map.get("11"));
        System.out.println(map.get("22"));
        System.out.println(map.get("33"));
        System.out.println(map.containsKey("33")+"---"+map.containsKey("11"));
    }

}
