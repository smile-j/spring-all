package mq;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.dong.starter.rocketmq.ProducterMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducterMain.class)
@Slf4j
public class MessageTypeTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *
     */
    @Test
    public void sendMsg(){

    }

    /**
     * 单向顺序消息
     * 参数三： 这些消息发送到哪个队列上
     */
    @Test
    public void sendOneWayMsgOrderly() throws InterruptedException {
        for (int i=10;i>0;i--){
            TimeUnit.SECONDS.sleep(1);
            rocketMQTemplate.sendOneWayOrderly("test-top-1:oneWay","这次一条单向消息"+format.format(new Date()),"xx");
        }
    }

    /**
     * 单向消息
     */
    @Test
    public void sendOneWayMsg() throws InterruptedException {
        for (int i=10;i>0;i--){
            TimeUnit.SECONDS.sleep(1);
            rocketMQTemplate.sendOneWay("test-top-1:oneWay","这次一条单向消息"+format.format(new Date()));
        }
    }

    /**
     * 异步消息
     */
    @SneakyThrows
    @Test
    public void sendAsyncMsg(){
        rocketMQTemplate.asyncSend("test-top-1:async", "异步消息：" + format.format(new Date()), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("--onSuccess--[{}]", JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("--onException--[{}]", JSON.toJSONString(throwable.getMessage()));

            }
        });
        log.info("==================================================");
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 同步消息
     */
    @Test
    public void testSendSyncMsg(){
        /**
         * 参数一：主题 topic:tag
         * 参数二：消息体
         * 参数三：超时时间 10秒
         */
        SendResult sendResult =
//                rocketMQTemplate.syncSend("test-top-1", "同步消息-"+format.format(new Date()), 10000);
                rocketMQTemplate.syncSend("test-top-1:demo", "同步消息-"+format.format(new Date()), 10000);
        System.out.println(sendResult);
    }


}
