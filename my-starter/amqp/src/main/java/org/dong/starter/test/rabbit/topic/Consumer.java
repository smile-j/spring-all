package org.dong.starter.test.rabbit.topic;

import com.rabbitmq.client.*;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/14
 */
public class Consumer {


    public static void main(String[] args) throws InterruptedException {

        List<String> routekeys = Arrays.asList("user.*","user.#");
        for(int i=0;i<routekeys.size();i++){
            String routekey = routekeys.get(i);
            final int ii = i;
            Thread t = new Thread(()->{
                try {
                    Connection connection = RabbitMqUtil.getConnection();
                    Channel channel = connection.createChannel();

                    channel.exchangeDeclare("topics","topic");
                    String queue = channel.queueDeclare().getQueue();
                    channel.queueBind(queue,"topics",routekey);
                    channel.basicConsume(queue,true,new DefaultConsumer(channel){
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            System.out.println(ii+"消费者："+new String(body));
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            t.join();
        }



    }

}
