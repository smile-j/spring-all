package org.dong.start.rocketmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/20
 */
@RestController
public class TestController {

    @GetMapping("/testGet")
    public String test(){
        return "hello world!!!";
    }

}
