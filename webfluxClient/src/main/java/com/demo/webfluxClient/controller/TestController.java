package com.demo.webfluxClient.controller;

import com.demo.webfluxClient.entity.UserEntity;
import com.demo.webfluxClient.service.IUserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private IUserApi userApi;

    @GetMapping("/test")
    public void test(){
        //测试信息提取
        //不订阅，不会实际发出请求，但会进入我们的代理类
//        userApi.getAllUser();
//        userApi.getUserById("111111");
        userApi.saveUser(
                Mono.just(UserEntity.builder().age(11).name("lili").build())
        ).subscribe(System.out::println);
//        userApi.deleteUserById("123");

        //直接调用调用实现rest接口的效果
//        Flux<UserEntity> users = userApi.getAllUser();
//        users.subscribe(System.out::println);

        String id = "5fd75b0957d54f197abb243b";
        userApi.getUserById(id).subscribe(
                userEntity -> System.out.println("getUserId:"+userEntity),
                e->System.err.println("没有查到,"+e)
        );
//        userApi.deleteUserById(id).subscribe();
        //创建用户
//        userApi.saveUser(Mono.just(UserEntity.builder().name("赵楼").age(29).build())).subscribe(System.out::println);
    }

}
