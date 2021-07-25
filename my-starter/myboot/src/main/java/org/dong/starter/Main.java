package org.dong.starter;


import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(DateConfig.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

}
