package org.dong.starter.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.dong.starter.rocketmq.vo.UserEntity;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/23
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "consumer-order",topic = "order-topic")
public class OrderListener implements RocketMQListener<UserEntity> {


    @Override
    public void onMessage(UserEntity user) {
        log.info("---msg,{}--",user);
    }
}
