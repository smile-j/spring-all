package com.dong.demo.controller;

import com.dong.demo.service.HelloClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("test")
public class TestController {

    @Value("${service-url.userServiceUrl}")
    private String userServiceUrl;

    @Autowired
    HelloClient client;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getXxxx")
    @GetMapping("testGet")
    public Object testGet() {

        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < 20; i++) {
            String res = client.hello();
//            String res = restTemplate.getForObject("http://user-server/user/hello",String.class);
//            String res = restTemplate.getForObject(userServiceUrl+"/user/hello",String.class);
            System.out.println("----->" + res);
            builder.append(res);
//        }
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


    //对controller层的接口做hystrix线程池隔离，可以起到限流的作用
    @HystrixCommand(
            commandKey = "helloCommand",//缺省为方法名
            threadPoolKey = "helloPool",//缺省为类名
            fallbackMethod = "fallbackMethod",//指定降级方法，在熔断和异常时会走降级方法
            commandProperties = {
                    //超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    //并发，缺省为10
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            }
    )
    @GetMapping("hello")
    public Object hello() throws InterruptedException {
        Random random = new Random();
        System.out.println("----start------>"+atomicInteger.getAndIncrement());
        int num = random.ints(100,1200).findFirst().getAsInt();
        System.out.println("---sleep-------"+num);
        Thread.sleep(num);
        System.out.println("----end------>"+atomicInteger.getAndDecrement());
        return "Hello World!：00000 "+ System.currentTimeMillis();
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

    /**
     *  降级方法，状态码返回503
     *  注意，降级方法的返回类型与形参要与原方法相同，可以多一个Throwable参数放到最后，用来获取异常信息
     */
    public String fallbackMethod(HttpServletResponse httpServletResponse, Throwable e){
        System.out.println("----------"+e.getMessage());
        httpServletResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return e.getMessage();
    }

    private AtomicInteger atomicInteger = new AtomicInteger();
    public String fallbackMethod(){
        System.out.println("----------");
        return "对不起，服务器太拥挤了！";
    }

}
