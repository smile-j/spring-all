/*
package com.dong.demo.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/1")
    public String get1(){
        log.info("get1 start");
        String res =  createStr();
        log.info("get1 end");
        return res;
    }


    @GetMapping("/2")
    public Mono<String> get2(){
        log.info("get2 start");
        Mono<String> res = Mono.fromSupplier(()->createStr());
        log.info("get2 end");
        return res;


//        return Mono.just("hello world");
    }


    @GetMapping(value = "/3",produces = MediaType.TEXT_EVENT_STREAM_VALUE )
    public Flux<String> flux(){
        log.info("get3 start");
        Flux<String> res = Flux.fromStream(IntStream.range(1,5).mapToObj(i->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux  -data -- "+i;
        }));
        log.info("get3 end");
        return res;


//        return Mono.just("hello world");
    }

    private String createStr(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world!!!";
    }
}
*/
