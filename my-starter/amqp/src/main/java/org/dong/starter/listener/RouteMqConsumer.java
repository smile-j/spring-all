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
public class RouteMqConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//构建临时队列
                    exchange = @Exchange(value = "directs",type = "direct"),//指定交换机名称和类型
                    key = {"info","warn","error"}
            )
    })
    public  void receive(String msg) {
        log.info("1消费者--"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//构建临时队列
                    exchange = @Exchange(value = "directs",type = "direct"),//指定交换机名称和类型
                    key = {"info"}
            )
    })
    public  void receive2(String msg) {
        log.info("2消费者--"+msg);
    }
}
