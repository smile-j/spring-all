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
public class TopicMqConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
            exchange = @Exchange(type = "topic",name="topics"),
                    key = {"user.save,user.*"}
            )
    })
    public  void receive(String msg) {
        log.info("1消费者--"+msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(type = "topic",name="topics"),
                    key = {"user.#","produce.#","order.#"}
            )
    })
    public  void receive2(String msg) {
        log.info("2消费者--"+msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(type = "topic",name="topics"),
                    key = {"user"}
            )
    })
    public  void receive3(String msg) {
        log.info("3消费者--"+msg);
    }
}
