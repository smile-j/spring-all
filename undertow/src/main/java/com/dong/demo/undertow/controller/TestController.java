package com.dong.demo.undertow.controller;

import com.alibaba.fastjson.JSONObject;
import com.dong.demo.undertow.service.LongTermTaskCallback;
import com.dong.demo.undertow.service.impl.LongTimeAsyncCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController {


    @Autowired
    private LongTimeAsyncCallService longTimeAsyncCallService;

    @GetMapping("/get")
    public Object testGet(){
        int sum = 0;
        for(int j=0;j<100000;j++){
            sum+=j;
        }
        return "hello world!!!"+sum;
    }

    @GetMapping("/testGetWithAsync")
    public WebAsyncTask<String> testCeilingWithAsync() {
        return new WebAsyncTask(() -> {
            int sum = 0;
            for(int j=0;j<1000000;j++){
                sum+=j;
            }
            TimeUnit.SECONDS.sleep(2);
            return sum+"---"+System.currentTimeMillis();
        });
    }

    @GetMapping(value="/asynctask")
    public DeferredResult<ModelAndView> asyncTask(){
        DeferredResult<ModelAndView> deferredResult = new DeferredResult<ModelAndView>();
        System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
        longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
            public void callback(Object result) {
                System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
                ModelAndView mav = new ModelAndView("remotecalltask");
                mav.addObject("result", result);
                deferredResult.setResult(mav);
            }
        });
        return deferredResult;
    }

    /**
     * 异步
     * @param obj
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/postAsync1")
    public Object testPostAsync(@RequestBody Object obj) throws ExecutionException, InterruptedException {
        log.info("------------>"+ JSONObject.toJSONString(obj));

        FutureTask futureTask = new FutureTask(()->{
            int sum = 0;
            for(int j=0;j<1000000;j++){
                sum+=j;
            }
            return sum;
        });
        new Thread(futureTask).start();
        return futureTask.get();
    }

    @PostMapping("/postAsync2")
    public Object testPostAsync2(@RequestBody Object obj) throws ExecutionException, InterruptedException {
        log.info("------------>"+ JSONObject.toJSONString(obj));

        FutureTask futureTask = new FutureTask(()->{
            int sum = 0;
            for(int j=0;j<1000000;j++){
                sum+=j;
            }
            return sum;
        });
        new Thread(futureTask).start();
        return futureTask.get();
    }

    @PostMapping("/postNoAsync")
    public Object testPostNoAsync(@RequestBody Object obj) throws ExecutionException, InterruptedException {
        log.info("------------>"+ JSONObject.toJSONString(obj));
         int sum = 0;
            for(int j=0;j<1000000;j++){
                sum+=j;
            }
            return sum;
    }

}
