package com.dong.demo.aop;

import com.dong.demo.annotate.LogFlag;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Aspect
public class LogAop {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private Logger logger = LoggerFactory.getLogger(LogAop.class);


    @Before("matchBean()")
    public void doBefore(JoinPoint joinPoint) {
        /*********************************************请求信息*******************************************/
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("REMOTE_IP : " + request.getRemoteAddr());


    }
    //匹配所有以Service结尾的bean里面的方法
    @Pointcut("bean(*Controller)")
    public void matchBean() {

    }

    @Pointcut("execution(* com.dong.demo.controller.TestController.*(..))")
    public void controllerMethodPointcut() {
    }

//    @Around("@annotation(logFlag)")
    @Around("matchBean()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable   {
        logger.info("--around---start----"+atomicInteger.getAndIncrement());
        try {
            Object o =  joinPoint.proceed();
//            Object o =  joinPoint.proceed(joinPoint.getArgs());
            return o;
        } finally {
            logger.info("--around---end----"+atomicInteger.getAndDecrement());
        }
    }

}
