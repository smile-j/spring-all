package com.dong.demo.jdk8.trick.ifesle;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserEntry2 {

    private String version = "v2";
    private String name;

}
