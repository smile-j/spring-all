package org.dong.starter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.dong.starter.rocketmq.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.beans.Transient;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/24
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送半事务消息
     *
     * setHeader可以用来传其他的参数
     */
    public void createOrderBefore(UserEntity user){
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_topic",
                MessageBuilder.withPayload(user).setHeader("tx-id","").build(),
                user);
    }

    public void createOrder(UserEntity userEntity){
        log.info("----save------");
    }

    public boolean getSaveResult() {
        return false;
    }
}
