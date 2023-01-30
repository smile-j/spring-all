package com.dong.demo.config;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configurable
@Slf4j
public class ThreadConfig {

//    private static final Logger logger = LoggerFactory.getLogger("ThreadConfig");


/*    @Bean(name = "executorService")
    public ExecutorService downloadExecutorService() {
        return new ThreadPoolExecutor(20, 40, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000),
                new ThreadFactoryBuilder().setNameFormat("defaultExecutorService-%d").build(),
                (r, executor) -> logger.error("defaultExecutor pool is full! "));
    }*/

}
