package org.dong.starter.producter.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/19
 */
@Component
@Slf4j
public class DelayListener {

    @RabbitListener(queues = "orderDtlQueue")
    public void receive(Message message, Channel channel) {
        try {
            log.info(message.getMessageProperties().getDeliveryTag()+"--消费者------"+new String(message.getBody()));
            System.out.println("延迟队列订单处理.........");
            log.info("处理业务逻辑......");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            log.error("receive-message异常：[{}]",e.getMessage());
            log.error("receive异常：[{}]",e);
            /* 第三个参数requeue是否重新回队列 true表示重新放回队列 */
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            } catch (Exception exception) {
                log.error("basicNack异常：[{}]",exception);
            }
        }
    }

}
