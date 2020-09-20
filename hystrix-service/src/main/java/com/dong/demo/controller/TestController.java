package com.dong.demo.controller;

import com.dong.demo.service.HelloClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    private RestTemplate restTemplate;

    @GetMapping("testGet")
    public Object testGet() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            String res = client.hello();
//            String res = restTemplate.getForObject("http://user-server/user/hello",String.class);
//            String res = restTemplate.getForObject(userServiceUrl+"/user/hello",String.class);
            System.out.println("----->" + res);
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

    @HystrixCommand(
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1200")}
    )
    public String getXxxx() {
        // ...省略代码逻辑
        return "456";
    }

    public String getHystrixNews(int a,int b) {
        // 做服务降级
        // 返回当前人数太多，请稍后查看
        return "123";
    }


}
