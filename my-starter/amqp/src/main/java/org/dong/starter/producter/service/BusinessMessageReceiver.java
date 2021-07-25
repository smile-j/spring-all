package org.dong.starter.producter.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author dongjunpeng
 * @Description  消费者
 * @date 2021/7/17
 */
//@Component
@Slf4j
public class BusinessMessageReceiver {

    private final String queueNameA ="business-queue-a";
    private final String queueNameB = "business-queue-b";

    /**
     * 业务A队列消费代码
     */
    @RabbitListener(queues = queueNameA)
    public void receiverA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("收到业务消息A：{}",msg);
        boolean ack = true;
        Exception exception = null;
        try{
            if (msg.contains("deadletter")){
                throw new RuntimeException("dead letter exception");
            }
        }catch (Exception e){
            ack = false;
            exception = e;
        }
        if (!ack){
            log.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);
            // 将信息重新放回到队列中
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }else{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
    }

    /**
     * 业务B队列消费代码
     */
    @RabbitListener(queues = queueNameB)
    public void receiverB(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("收到业务消息B：{}",msg);
        // 手动消息确认步骤 配合acknowledge-mode: manual 模式
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


}
