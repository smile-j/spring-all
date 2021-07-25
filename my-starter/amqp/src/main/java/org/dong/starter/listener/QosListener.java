package org.dong.starter.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 * consumer 限流机制
 *
 * @author dongjunpeng
 * @Description
 * @date 2021/7/18
 */
//@Component
@Slf4j
public class QosListener {

    @RabbitListener(queues = "demo_return_queue")
    public void receive(Message message, Channel channel) {
        try {
//            channel.basicQos(0,1,false);
            channel.basicQos(1);
            log.info("消费者------"+new String(message.getBody()));
            log.info("处理业务逻辑......");
            int a = 3/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            log.error("receive-message异常：[{}]",e.getMessage());
            log.error("receive异常：[{}]",e);
            /* 第三个参数requeue是否重新回队列 true表示重新放回队列 */
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            } catch (IOException ioException) {
                log.error("receive异常：[{}]",ioException);
            }
        }finally {
            System.out.println("--------------------------------------------");
        }
    }

}
