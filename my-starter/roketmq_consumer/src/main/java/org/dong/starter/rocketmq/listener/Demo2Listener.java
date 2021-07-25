package org.dong.starter.rocketmq.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/24
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "test-top-1",consumerGroup = "demo-consumer-2")
public class Demo2Listener implements RocketMQListener {


    @Override
    public void onMessage(Object o) {
        log.info("==demo-consumer-2=====msg:[{}]======", JSON.toJSONString(o));
    }
}
