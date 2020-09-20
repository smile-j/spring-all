package com.dong.demo.controller;

import com.dong.demo.service.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("testGet")
    public Object testGet(){

     StringBuilder builder = new StringBuilder();
        for(int i=0;i<10;i++){
//            String res = client.hello();
//            String res = restTemplate.getForObject("http://user-server/user/hello",String.class);
            String res = restTemplate.getForObject(userServiceUrl+"/user/hello",String.class);
            System.out.println("----->"+res);
            builder.append(res);
        }
    return builder.toString();
    }


}
