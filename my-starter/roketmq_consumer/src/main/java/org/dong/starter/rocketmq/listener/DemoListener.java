package org.dong.starter.rocketmq.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/24
 *
 *
 *
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "test-top-1",//主题
        consumerGroup = "demo-consumer-1",//消费者组
        consumeMode = ConsumeMode.CONCURRENTLY, //消费模式 ConsumeMode.ORDERLY(顺序) ConsumeMode.CONCURRENTLY(同步，默认)
        messageModel = MessageModel.CLUSTERING //消息模式 BROADCASTING(广播模式) ,CLUSTERING(集群模式，模式)
)
public class DemoListener implements RocketMQListener {


    @Override
    public void onMessage(Object o) {
        log.info("==demo-consumer-1=====msg:[{}]======", JSON.toJSONString(o));
    }
}
