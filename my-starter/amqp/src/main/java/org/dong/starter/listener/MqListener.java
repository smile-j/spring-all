package org.dong.starter.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/14
 */

/* 持久化 非独占 不是自动删除 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class MqListener {



    @RabbitHandler
    public void receivel(String message){
        System.out.println("消费者："+message);
    }


}
