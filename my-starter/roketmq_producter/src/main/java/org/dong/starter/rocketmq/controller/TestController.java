package org.dong.starter.rocketmq.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.dong.starter.rocketmq.OrderService;
import org.dong.starter.rocketmq.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/23
 */
@RestController
public class TestController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private OrderService orderService;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("producerMsg")
    public Object producerMsg(){

        rocketMQTemplate.convertAndSend("order-topic",
                new UserEntity().setAge(11).setName("Lili").setBirth(format.format(new Date())));
        return "OK";
    }

    @GetMapping("testTrans")
    public Object testTrans(){
        orderService.createOrderBefore(new UserEntity().setAge(11).setName("LiLei").setBirth(format.format(new Date())));
        return "OK";
    }

}
