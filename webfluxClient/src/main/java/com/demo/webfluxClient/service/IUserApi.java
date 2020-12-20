package com.demo.webfluxClient.service;

import base.annotations.ApiServer;
import com.demo.webfluxClient.entity.UserEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ApiServer("http://localhost:8080/user")
public interface IUserApi {

    @GetMapping("/all")
    Flux<UserEntity> getAllUser();

    @GetMapping("/get/{id}")
    Mono<UserEntity> getUserById(@PathVariable("id") String id);

    @DeleteMapping("/delete/{id}")
    Mono<Void> deleteUserById(@PathVariable("id") String id);

    @PostMapping("/createUser")
    Mono<UserEntity> saveUser(@RequestBody Mono<UserEntity> user);

}
