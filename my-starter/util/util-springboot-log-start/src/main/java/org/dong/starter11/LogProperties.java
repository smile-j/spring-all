package org.dong.starter11;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/12/16
 */
@ConfigurationProperties("util.log")
public class LogProperties {

    private Set<String> ignore;

    private String name="默认拦截级别";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
