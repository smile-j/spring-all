package org.dong.starter.test.rabbit.fanout;

import com.rabbitmq.client.*;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @author dongjunpeng
 * @Description   routingKey对于 fanout没有什么用
 * @date 2021/7/13
 */
public class Consumer {

    public static void main(String[] args) throws IOException {


        for (int i=0;i<5;i++){
            final int ii = i;
            new Thread(()->{

                try {
                    Connection connection = RabbitMqUtil.getConnection();
                    Channel channel = connection.createChannel();

//                      channel.exchangeBind("logs","fanout","");
                    channel.exchangeDeclare("logs","fanout");
                    //临时队列
                    String queue = channel.queueDeclare().getQueue();
                    //绑定队列与交换机  routingKey对于 fanout没有什么用
                    channel.queueBind(queue,"logs","");

                    //消费消息
                    channel.basicConsume(queue,true,new DefaultConsumer(channel){
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                            System.out.println(ii+"消费者："+new String(body));

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }


            },"线程"+i).start();
        }



    }

}
