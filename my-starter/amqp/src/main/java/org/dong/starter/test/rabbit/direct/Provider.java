package org.dong.starter.test.rabbit.direct;

import com.rabbitmq.client.*;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.Date;

/**
 * @author dongjunpeng
 * @Description Direct 完全匹配，单播模式
 * @date 2021/7/13
 */
public class Provider {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //通过通道生命交换机  参数1：交换机的名称，参数2：路由模式
        channel.exchangeDeclare("log_direct","direct");
        String routingkey = "info";
        for (int i=2;i>0;i--){
            channel.basicPublish("log_direct",routingkey,null,(i+"-routingkey-"+"log_direct!--").getBytes());
        }
        channel.basicPublish("log_direct","error",null,("error-routingkey-"+"log_direct!--").getBytes());


        RabbitMqUtil.closeConnectionAndChanel(connection,channel);

    }

}
