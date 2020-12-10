package com.dong.demo.entity;

import com.dong.demo.enums.GenderEnum;
import com.dong.demo.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private String name;
    private Integer age;
    private Grade grade;
    private GenderEnum gender;

}
