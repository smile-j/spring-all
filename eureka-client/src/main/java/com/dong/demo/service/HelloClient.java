package com.dong.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("user-server")
public interface HelloClient {
    @RequestMapping(value = "/user/hello", method = GET)
    String hello();
}
