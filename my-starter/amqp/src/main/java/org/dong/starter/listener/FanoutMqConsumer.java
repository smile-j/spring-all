package org.dong.starter.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/15
 */
@Component
@Slf4j
public class FanoutMqConsumer {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,//临时队列
            exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机
            )
    })
    public void receive(String msg){
        log.info("消费者1------"+msg);
    }


    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,//临时队列
            exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机
    )
    })
    public void receive2(String msg){
        log.info("消费者2------"+msg);
    }

}
