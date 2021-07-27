package org.dong.start.rocketmq.test.batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dongjunpeng
 * @Description 批量发送  大小不能大与4M
 *
 */
public class Producter {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //1．创建消息生产者，指定生产者所属的组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        //2．指定Nameserver地址
        producer.setNamesrvAddr ("127.0.0.1:9876");
        //3．启动生产者
        producer.start();
        List<Message> msgs = new LinkedList<>();
        for(int i=10;i>0;i--){
            //4．创建消息对象,指定主题、标签和消息体
            Message msg = new Message( "batch" , "Tag1" ,(format.format(new Date())+"=hello world "+i).getBytes(StandardCharsets.UTF_8));
            msgs.add(msg);
        }
        SendResult sendResult = producer.send(msgs) ;
        System.out.println(sendResult);
        //6．关闭生产者
        producer.shutdown();

    }

}
