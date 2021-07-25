package org.dong.starter.test.rabbit.workQueue;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/12
 */
public class ConsumerAck {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只消费一个消息
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("消费者-1"+msg);
//                TimeUnit.SECONDS.sleep(1);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });



    }

}

