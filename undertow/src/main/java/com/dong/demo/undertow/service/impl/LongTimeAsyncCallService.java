package com.dong.demo.undertow.service.impl;

import com.dong.demo.undertow.service.LongTermTaskCallback;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class LongTimeAsyncCallService {

    private final int CorePoolSize = 4;
    private final int NeedSeconds = 3;
    private Random random = new Random();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);
    public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallback callback){
        System.out.println("完成此任务需要 : " + NeedSeconds + " 秒");
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                callback.callback("长时间异步调用完成.");
            }
        }, 10,TimeUnit.SECONDS);
    }

}
