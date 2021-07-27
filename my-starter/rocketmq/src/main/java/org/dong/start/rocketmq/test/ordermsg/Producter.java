package org.dong.start.rocketmq.test.ordermsg;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.dong.start.rocketmq.vo.OrderVo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author dongjunpeng
 * @Description 延迟队列
 *  现在RocketMq并不支持任意时间的延时，需要设置几个固定的延时等级，从1s到2h分别对应着等级1到18 消息消费失败会进入延时消息队列，消息发送时间与设置的延时等级和重试次数有关，详见代码SendMessageProcessor.java
 *private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
 * @date 2021/7/27
 */
public class Producter {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

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

}
