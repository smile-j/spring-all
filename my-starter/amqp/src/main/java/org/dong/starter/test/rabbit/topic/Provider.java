package org.dong.starter.test.rabbit.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author dongjunpeng
 * @Description topic 规则匹配，模糊匹配 *代表匹配1个单词，#代表匹配0个或多个单词
 * @date 2021/7/14
 */
public class Provider {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //通过通道生命交换机  参数1：交换机的名称，参数2：路由模式
        channel.exchangeDeclare("topics","topic");

        String routekey ="user";
        List<String> routekeys=Arrays.asList("user.save","");
        channel.basicPublish("topics",routekey,null,("topic动态路由,routekey:["+routekey+"]").getBytes());
        RabbitMqUtil.closeConnectionAndChanel(connection,channel);
    }

}
