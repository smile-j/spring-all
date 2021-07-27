package org.dong.start.rocketmq.test.filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dongjunpeng
 * @Description 过滤消息
 * @date 2021/7/27
 */
public class Producter {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        for(int i=16;i>0;i--){
            Message message = new Message("filter-topic","filter",(i+"==Hello RocketMQ "+format.format(new Date())).getBytes(StandardCharsets.UTF_8));
            message.putUserProperty("a",String.valueOf(i%3));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }
        producer.shutdown();

    }

}
