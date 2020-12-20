package com.dong.demo.webflux.controller;


import com.dong.demo.webflux.dao.UserRepository;
import com.dong.demo.webflux.entity.User;
import com.dong.demo.webflux.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.awt.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserContorller {

//    @Autowired
//    private UserRepository userRepository;

    private final UserRepository userRepository;
    public UserContorller(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public Flux<User> getAll(){
        return userRepository.findAll();
    };

    @GetMapping(value = "/streamAll",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }

    @PostMapping("/createUser")
    public Mono<User> createUser(@RequestBody @Valid User user){
        log.info("createUser:"+user);
        //spring dta jpa 里面，新增和修改都是save,有id是修改，id为空是新增
        //根据实际情况是否置空id
        user.setId(null);
        CheckUtil.checkName(user.getName());
        return this.userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delUser(@PathVariable("id") String id){
        log.info("id:"+ id);
        //没有返回值
//        this.userRepository.deleteById(id);
       return this.userRepository.findById(id)
        //当你操作数据，并返回一个Mono这个实际使用flatMap
        //如果不操作数据，只是转换数据，使用map
        .flatMap(user -> this.userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<User>> updateUser(
            @PathVariable("id") String id,
            @Valid  @RequestBody User user ){
        log.info("id:"+ id);
        //没有返回值
//        this.userRepository.deleteById(id);
        return this.userRepository.findById(id)
                //当你操作数据，并返回一个Mono这个实际使用flatMap
                //如果不操作数据，只是转换数据，使用map
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                })
                .map(u->new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<User>> getUserByid(@PathVariable("id") String id){
        log.info("id:"+ id);
        //没有返回值
//        this.userRepository.deleteById(id);
        return this.userRepository.findById(id)
                //当你操作数据，并返回一个Mono这个实际使用flatMap
                //如果不操作数据，只是转换数据，使用map
                .map(u->new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{start}/{end}")
    public Flux<User> findUsersByAge(
            @PathVariable("start") int start
            ,@PathVariable("end") int end){
        log.info("findUsersByAge"+ start+"--"+end);

        return this.userRepository.findByAgeBetween(start,end);
    }

    @GetMapping( value = "/stream/age/{start}/{end}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindUsersByAge(
            @PathVariable("start") int start
            ,@PathVariable("end") int end){
        log.info("findUsersByAge"+ start+"--"+end);

        return this.userRepository.findByAgeBetween(start,end);
    }


    @GetMapping("/old")
    public Flux<User> oldUser(){

        return this.userRepository.oldUser();
    }

    @GetMapping( value = "/stream/old",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamoldUser(){
        log.info("findUsersByAge");

        return this.userRepository.oldUser();
    }
}

