package org.dong.starter.test.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.dong.starter.util.RabbitMqUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description  生产者消费者 1:1
 * @date 2021/6/22
 */
public class Provider {



    public static void main(String args[]) throws Exception {
       /* ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitMqUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //通过绑定对应的消息队列
        //参数1.队列的名字，如果不存在，自动创建
        //参数2.durable 队列是否持久化
        //参数3.exclusive 是否独占队列
        //参数4.autoDelete 是否消费完成后自动删除
        //参数5.额外的
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1. 交换机
        //参数2.队列名称
        //参数3.传递消息额外设置
        //参数4.消息具体的内容
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
       for(int i=0;i<10;i++){
           TimeUnit.SECONDS.sleep(1);
           channel.basicPublish("","hello",null,("hello rabbmitmq!--"+format.format(new Date())).getBytes());
//           channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,("hello rabbmitmq!--"+format.format(new Date())).getBytes());
       }

        RabbitMqUtil.closeConnectionAndChanel(connection,channel);
        /*channel.close();
        connection.close();*/
    }

}
