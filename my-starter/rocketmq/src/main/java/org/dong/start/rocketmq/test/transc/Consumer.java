package org.dong.start.rocketmq.test.transc;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author dongjunpeng
 * @Description  发送事务消息
 *  事务消息共有三种状态，提交状态、回滚状态、中间状态
 *  TransactionStatus.CommitTransaction: 提交事务，它允许消费者消费此消息。
 * TransactionStatus.RollbackTransaction: 回滚事务，它代表该消息将被删除，不允许被消费。
 * TransactionStatus.Unknown: 中间状态，它代表需要检查消息队列来确定状态。
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {

        //        1.创建消息消费者,指定消费者所属的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myconsumer-group-1");
//        ⒉指定Nameserver地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
//        3.订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("DelayTopic","*");

        //设置消费模式  负载均衡（默认）|广播模式
//        consumer.setMessageModel(MessageModel.BROADCASTING);

        //MessageListenerOrderly
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext orderlyContext) {
                System.out.println("============="+list.size());
                for (MessageExt msg:list){
                    System.out.println(msg.getMsgId()+"--msg:"+new String(msg.getBody())+"===延迟时间："+(System.currentTimeMillis()-msg.getStoreTimestamp())
                            +"---detail"+JSON.toJSONString(msg));
                }
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
//        5.启动消息消费者
        consumer.start();
        System.out.printf("Consumer Started.%n");

    }

}
