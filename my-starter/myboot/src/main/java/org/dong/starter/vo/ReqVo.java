package org.dong.starter.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ReqVo {
    @NotBlank(message = "名字不能为空")
    private String name;
    @NotEmpty(message = "地址不能为空")
    private List<String> adds;

}
