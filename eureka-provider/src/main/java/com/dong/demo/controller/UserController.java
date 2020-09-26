package com.dong.demo.controller;

import com.dong.demo.apimodel.model.User;
import com.dong.demo.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    private IUserService userService;

    @PostMapping("/saveUser")
    public Object saveUser(@RequestBody User user) throws JsonProcessingException {
        return userService.saveUser(user);
    }

    @GetMapping("findUsers")
    public Object findAllUsers() throws JsonProcessingException {

        return userService.findAlls(null);

    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException {

        Random random = new Random();

        List<ServiceInstance> instances = client.getInstances("user-server");
        ServiceInstance selectedInstance = instances
                .get(new Random().nextInt(instances.size()));
        int num = random.ints(500,1500).findFirst().getAsInt();
        logger.info(".....sleep ...."+num);
//        Thread.sleep(num);
        return "Hello World: " + selectedInstance.getServiceId() + ":" + selectedInstance
                .getHost() + ":" + selectedInstance.getPort();
    }

    @GetMapping("/getUserById/{id}")
    public String getUserById(@PathVariable("id") Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        User user = new User();
        user.setId(id);
        user.setName("张三");
        user.setAddr("北京");
        user.setBorn(new Date());
        int num = random.ints(1,1000).findFirst().getAsInt();
        logger.info(".....sleep ...."+num);
        return objectMapper.writeValueAsString(user);
    }

    @GetMapping("testMath")
    public Object testMath(@RequestParam(name="a") int a, @RequestParam(name = "b") int b){
        logger.info(a+"--testMath----->"+b);
        return a/b;
    }

}
