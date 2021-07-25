package org.dong.starter.test.rabbit.workQueue;

import com.rabbitmq.client.*;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/12
 */
public class ConsumerAck2 {

    public static void main(String[] args) throws IOException, InterruptedException {


        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只消费一个消息
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("消费者2-"+msg);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

        TimeUnit.SECONDS.sleep(1000);

    }

}
