package com.dong.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("user-server")
public interface HelloClient {
    @RequestMapping(value = "/user/hello", method = GET)
    String hello();

    @GetMapping("/user/getUserById/{id}")
    String getUserById(@PathVariable("id") Long id);

    @GetMapping("/user/testMath")
    public Object testMath(@RequestParam(name="a") int a, @RequestParam(name = "b") int b);
}
