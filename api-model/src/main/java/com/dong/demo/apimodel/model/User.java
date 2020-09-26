package com.dong.demo.apimodel.model;

import lombok.Data;

import java.util.Date;

@Data
public class    User {

    private Long id;
    private String name;
    private String addr;
    private Date born;

}
