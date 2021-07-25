package org.dong.starter.test.rabbit.workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/12
 */
public class Provider {

    public static void main(String[] args) throws IOException, InterruptedException {

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //
        channel.queueDeclare("work",true,false,false,null);

        //生产消息
//        channel.basicPublish("","work",null,"hello work queue".getBytes());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        for(int i=0;i<20;i++){
//            TimeUnit.SECONDS.sleep(1);
//            channel.basicPublish("","work",null,("hello work queue--"+format.format(new Date())).getBytes());
            channel.basicPublish("","work",null,(i+"").getBytes());
        }

        RabbitMqUtil.closeConnectionAndChanel(connection,channel);

    }

}
