package com.dong.demo.service;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloClient2FallbackFactory implements FallbackFactory<HelloClient> {

    private Logger logger = LoggerFactory.getLogger(HelloClient2FallbackFactory.class);

    @Override
    public HelloClient create(Throwable throwable) {
        return new HelloClient() {
            @Override
            public String hello() {
                logger.info(throwable.getMessage());
                logger.info(throwable.getClass().getName());
                return "请求另一个服务失败!!!";
            }

            @Override
            public String getUserById(Long id) {
                logger.info(throwable.getMessage());
                logger.info(throwable.getClass().getName());
                logger.info("param:id:{}",id);
                return "error!!!";
            }

            @Override
            public Object testMath(int a, int b) {
                logger.info(throwable.getMessage());
                logger.info(throwable.getClass().getName());
                logger.info("params:a:{},b:{}",a,b);
                return 110;
            }
        };
    }
}
