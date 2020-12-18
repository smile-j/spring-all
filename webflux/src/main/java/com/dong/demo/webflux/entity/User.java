package com.dong.demo.webflux.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


@Data
@Document
public class User {

    @Id
    private String id;
    @NotBlank
    private String name;

    @Range(min = 10,max = 100)
    private int age;

}
