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
 * consumer ACK机制:
 * 1．设手动签收。acknowledge= "manual"
 * 2．让监听器类实现channelAwareMessageListener接口
 * 3．如果消息成功处理,则调用channel的basicAck()签收
 * 4．如果消息处理失败，则调用channel的basicNack()拒绝签收，broker重新发送给consumer
 *
 * @author dongjunpeng
 * @Description ack 手动接受
 * @date 2021/7/18
 */
@Component
@Slf4j
public class AckListener {

    @RabbitListener(queues = "demo_return_queue")
    public void receive(Message message, Channel channel) {
        try {
            TimeUnit.SECONDS.sleep(2);
            log.info("消费者------"+new String(message.getBody()));
            log.info("处理业务逻辑......");
//            int i = 3/0;
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        }catch (Exception e){
            log.error("receive-message异常：[{}]",e.getMessage());
            log.error("receive异常：[{}]",e);
            /* 第三个参数requeue是否重新回队列 true表示重新放回队列 */
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,true);
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            } catch (IOException ioException) {
                log.error("receive异常：[{}]",ioException);
            }
        }
    }

}
