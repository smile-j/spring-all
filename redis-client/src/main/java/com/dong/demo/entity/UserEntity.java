package com.dong.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private String add;
    private Date born;

}
