package org.dong.starter.test.rabbit;

import com.rabbitmq.client.*;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/12
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

       /* ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
        //创建连接
        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitMqUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定对象
        channel.queueDeclare("hello",false,false,false,null);

        //消费消息
        //参数1.消费那个队列的消息  队列名称
        //参数2. 开启消息启动确认机制
        //参数3. 消费时的回调接口
      /*  channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("消费消息："+msg);
            }
        });*/
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            /**
             *
             * @param consumerTag 标识
             * @param envelope 路由，交换机的信息
             * @param properties
             * @param body
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("consumerTag:"+consumerTag);
                System.out.println("getExchange:"+envelope.getExchange());
                System.out.println("getRoutingKey:"+envelope.getRoutingKey());
                System.out.println("properties:"+properties);

                String msg = new String(body);
                System.out.println("消费消息："+msg);
            }
        });

//        channel.close();
//        connection.close();
    }

}
