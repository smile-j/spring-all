package com.dong.demo.scheder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Cron {

    @Scheduled(cron = "30 * * * * ?")
    @Async
    public void test1(){
        log.info("test1....start");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test1....end");
    }


    @Scheduled(cron = "0 0/2 * * * ?")
    @Async("asyncJobsExecutor")
    public void test2(){
        log.info("test2....start");
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test2....end");
    }

}
