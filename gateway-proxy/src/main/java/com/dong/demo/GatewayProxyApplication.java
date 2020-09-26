package com.dong.demo;

import com.dong.demo.config.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayProxyApplication.class, args);
    }


     @Bean
     public UriKeyResolver uriKeyResolver() {
               return new UriKeyResolver();
            }

}
