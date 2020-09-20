package com.dong.demo.controller;

import com.dong.demo.service.HelloClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("test")
public class TestController {

    @Value("${service-url.userServiceUrl}")
    private String userServiceUrl;

    @Autowired
    HelloClient client;

    @Autowired
    private RestTemplate restTemplate ;

    @HystrixCommand(fallbackMethod = "hello")
    @GetMapping("testGet")
    public Object testGet(){

     StringBuilder builder = new StringBuilder();
        for(int i=0;i<10;i++){
            String res = client.hello();
//            String res = restTemplate.getForObject("http://user-server/user/hello",String.class);
//            String res = restTemplate.getForObject(userServiceUrl+"/user/hello",String.class);
            System.out.println("----->"+res);
            builder.append(res);
        }
    return builder.toString();
    }

    @GetMapping("/getUserById/{id}")
    String getUserById(@PathVariable("id") Long id){
        return client.getUserById(id);
    }

    @HystrixCommand(fallbackMethod = "getHystrixNews")
    @GetMapping("testMath")
    public Object testMath(@RequestParam(name="a") int a, @RequestParam(name = "b") int b){

        return client.testMath(a,b);

    }

    public String getHystrixNews(int a,int b) {
        // 做服务降级
        // 返回当前人数太多，请稍后查看
        return "服务降级！";
    }

    public String hello() {
        // 做服务降级
        // 返回当前人数太多，请稍后查看
        return "hello 服务降级！";
    }

}
