package com.dong.demo.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import org.springframework.context.annotation.Bean;

public class MyRule  {

//    @Bean
    public IRule myselfRule() {
        // 指定策略：我们自定义的策略
        return new CustomRule();
    }

}
