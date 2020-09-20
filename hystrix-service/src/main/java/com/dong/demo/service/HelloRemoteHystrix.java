package com.dong.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Hystrix功能：超时；异常
 *
 */

@Component
public class HelloRemoteHystrix implements HelloClient{

    private Logger logger = LoggerFactory.getLogger(HelloRemoteHystrix.class);

    @Override
    public String hello() {
        return "请求另一个服务失败!";
    }

    @Override
    public String getUserById(Long id) {
        logger.info("param:id:{}",id);
        return "error";
    }

    @Override
    public Object testMath(int a, int b) {
        logger.info("params:a:{},b:{}",a,b);
        return 110;
    }
}
