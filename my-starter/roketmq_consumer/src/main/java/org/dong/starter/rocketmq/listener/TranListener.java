package org.dong.starter.rocketmq.listener;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.dong.starter.rocketmq.vo.UserEntity;
import org.springframework.stereotype.Component;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/24
 */
@Component
@RocketMQMessageListener(topic = "",consumerGroup = "")
public class TranListener implements TransactionListener {
    /**
     * 执行本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        return null;
    }

    /**
     * 消息会查
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return null;
    }
}
