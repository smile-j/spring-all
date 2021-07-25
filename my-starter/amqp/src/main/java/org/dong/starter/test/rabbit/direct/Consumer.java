package org.dong.starter.test.rabbit.direct;

import com.rabbitmq.client.*;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/13
 */
public class Consumer {

    public static void main(String[] args) throws InterruptedException {

        List<String> infos = Arrays.asList("info","error","warning");

        for (int i = 0;i<3;i++){
            final int ii = i;
            Thread t = new Thread(()->{
                try {
                    Connection connection = RabbitMqUtil.getConnection();
                    Channel channel = connection.createChannel();

                    channel.exchangeDeclare("log_direct","direct");

                    String queue = channel.queueDeclare().getQueue();
                    channel.queueBind(queue,"log_direct","info");
                    if(ii==1){
                        channel.queueBind(queue,"log_direct","info");
                        channel.queueBind(queue,"log_direct","error");
                        channel.queueBind(queue,"log_direct","warning");

                    }

                    channel.basicConsume(queue,true,new DefaultConsumer(channel){
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            System.out.println(ii+"消费者："+new String(body));

                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            t.start();
            t.join();
        }



    }

}
