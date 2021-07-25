package org.dong.starter.test.rabbit.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @author dongjunpeng
 * @Description 每个发到fanout类型交换器的消息都会分到所有绑定的队列上，广播模式
 *              与 routingKey无关
 * @date 2021/7/13
 */
public class Provider {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //将生命的通道绑定交换机
        //参数1 交换机名称
        //参数2 广播类型
        channel.exchangeDeclare("logs","fanout");

        for (int i=0;i<2;i++){
            channel.basicPublish("logs","",null,(i+"fanout message type").getBytes());
        }

        RabbitMqUtil.closeConnectionAndChanel(connection,channel);

    }

}
