package org.dong.starter.producter.controller;

import io.swagger.annotations.Api;
import org.dong.starter.vo.ReqVo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "查询人员信息", tags = "查询有关用户相关信息")
@RestController
@RequestMapping("/user")
public interface TestInterface {

    @GetMapping("testStr")
    public Object testStr();
    @PostMapping("postValid")
    public Object testPost(@Validated @RequestBody ReqVo reqVo, BindingResult bindingResult)  ;
}
