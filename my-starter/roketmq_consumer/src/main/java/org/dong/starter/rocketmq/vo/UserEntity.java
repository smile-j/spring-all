package org.dong.starter.rocketmq.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/23
 */
@Data
@Accessors(chain = true)
public class UserEntity {

    private String name;
    private Integer age;
    private String birth;

}
