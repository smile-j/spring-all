package org.dong.start.rocketmq.test;

import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.dong.start.rocketmq.vo.OrderVo;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/21
 */
public class SendMsg {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, Exception {
//        syncProducer();
        sendOrderMsg();

      //1．创建消息生产者，指定生产者所属的组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        //2．指定Nameserver地址
        producer.setNamesrvAddr ("127.0.0.1:9876");
        //同步发送失败重试次数
        producer.setRetryTimesWhenSendFailed(2);
        //3．启动生产者
        producer.start();
        //4．创建消息对象,指定主题、标签和消息体
        Message msg = new Message( "myTopic" , "*" ,("test RocketMQ Message").getBytes());
         //5．发送消息 第二个参数 超时时间
        SendResult sendResult = producer.send(msg, 10000) ;
        System.out.println(sendResult);
        //6．关闭生产者
        producer.shutdown();


    }

    /**
     * 延迟消息 固定时间
     */
    public static void sendDelayMsg(){

    }

    //发送顺序消息
    public static void sendOrderMsg() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        List<OrderVo> orderVos = Arrays.<OrderVo>asList(
                new OrderVo().setOrderId(1039L).setDesc("创建"),new OrderVo().setOrderId(1063L).setDesc("创建"),
                new OrderVo().setOrderId(2039L).setDesc("创建"),new OrderVo().setOrderId(1039L).setDesc("付款"),
                new OrderVo().setOrderId(1063L).setDesc("付款"),new OrderVo().setOrderId(1039L).setDesc("推送"),
                new OrderVo().setOrderId(2039L).setDesc("付款"),new OrderVo().setOrderId(1039L).setDesc("完成")
        );

        //1．创建消息生产者，指定生产者所属的组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        //2．指定Nameserver地址
        producer.setNamesrvAddr ("127.0.0.1:9876");
        //3．启动生产者
        producer.start();
        int i =0;
        for(OrderVo vo:orderVos){
            i++;
            //4．创建消息对象,指定主题、标签和消息体
            Message msg = new Message( "orderTopic" , "order" ,"i"+i, JSON.toJSONString(vo).getBytes());
            /**
             *
             * 发送消息 第二个参数 队列选择的选择器
             *  第三个参数  选择队列的业务表示（订单id）
             */
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                /**
                 *
                 * @param list  消息队列的集合
                 * @param message 消息对象
                 * @param o 业务标识参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    long oderId = (long) o;
                    long index = oderId%list.size();
                    return list.get((int) index);
                }
            },vo.getOrderId()) ;
            System.out.println(sendResult);
        }
        //6．关闭生产者
        producer.shutdown();

    }

    //发送单向消息
    public static void sendOneWayMsg() throws MQClientException {

        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        Message msg = new Message("","","".getBytes(StandardCharsets.UTF_8));

    }

    //同步
    public static void syncProducer() throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        //同步发送失败重试次数
        producer.setRetryTimesWhenSendFailed(2);
        producer.start();
        for (int i=20;i>0;i--){
            Message msg = new Message("myTopic","myTag",("hello rocketmq "+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg,1000);
            System.out.printf("%s%n", sendResult);

        }
        producer.shutdown();
    }

    //异步
    public void AsyncProducer() throws Exception{
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        //发送失败重试次数  不会重新选择其他的borker
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;
        // 根据消息数量实例化倒计时计算器
        final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("myTopic",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            // SendCallback接收异步返回结果的回调
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        // 等待5s
        countDownLatch.await(5, TimeUnit.SECONDS);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
