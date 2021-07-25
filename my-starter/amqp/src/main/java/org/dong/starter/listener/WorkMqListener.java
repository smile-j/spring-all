package org.dong.starter.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/15
 */

@Component
public class WorkMqListener {

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive(String msg){
        System.out.println("消费者1："+msg);
    }
    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive2(String msg){
        System.out.println("消费者2："+msg);
    }

}
