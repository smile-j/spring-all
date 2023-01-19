package com.dong.demo.jdk8.trick.ifesle;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserEntry {

    private String version = "v1";
    private String name;

}
