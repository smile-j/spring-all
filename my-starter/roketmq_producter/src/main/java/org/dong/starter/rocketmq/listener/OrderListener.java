package org.dong.starter.rocketmq.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.*;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.dong.starter.rocketmq.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/24
 */

@Component
@Slf4j
@RocketMQTransactionListener
public class OrderListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;


    /**
     * 执行本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
//        message.getHeaders().get("tx-id");//用来传参数
        log.info("------message-----{}", JSON.toJSONString(message));
        log.info("------Object-----{}",JSON.toJSONString(o));
        try {
            orderService.createOrder(null);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 消息回查
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        if (orderService.getSaveResult()){
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
