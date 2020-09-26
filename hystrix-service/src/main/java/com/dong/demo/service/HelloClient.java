package com.dong.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "user-server")
//@FeignClient(value = "user-server",fallback = HelloRemoteHystrix.class,configuration= FeignClientsConfiguration.class)
//fallbackFactory 指定一个fallback工厂,与指定fallback不同, 此工厂可以用来获取到触发断路器的异常信息,TestClientFallbackFactory需要实现FallbackFactory类
//@FeignClient(value="user-server",configuration=FeignClientsConfiguration.class,fallbackFactory=HelloClient2FallbackFactory.class)
public interface HelloClient {
    @GetMapping(value = "/user/hello")
    String hello();

    @GetMapping("/user/getUserById/{id}")
    String getUserById(@PathVariable("id") Long id);

//    @HystrixCommand(fallbackMethod = "testMath", ignoreExceptions = {NullPointerException.class})
    @GetMapping("/user/testMath")
    public Object testMath(@RequestParam(name="a") int a, @RequestParam(name = "b") int b);
}
