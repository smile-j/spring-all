package org.dong.starter.producter.service;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.dong.starter.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dongjunpeng
 * @Description  消息生产者往mq推送消息
 * @date 2021/7/17
 */
//@Service
@Slf4j
public class RabbitMqSendMsg {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 相应效率很慢
     * @param msg
     */
    public void sendMsg(String msg){
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(System.currentTimeMillis());
        rabbitTemplate.convertSendAndReceive(RabbitConfig.BUSINESS_ORDER_EXCHANGE, "", msg);
        //rabbitTemplate.convertAndSend(RabbitMQConfig.BUSINESS_ORDER_EXCHANGE, "", msg);
        log.info(Thread.currentThread().getName()+"耗时={}",System.currentTimeMillis() - (Long)threadLocal.get());
    }


    /**
     * 优化：
     * 提高并发量
     * 提高相应速度 避免客户端长时间等待 后端异步去操作
     * @param msg
     */
    public void sendOrderMsg(String msg){
        ThreadUtil.execute(new SendMsg(msg));
    }

    // 异步发送信息

    class SendMsg implements Runnable{

        private String msg;

        public SendMsg(String msg){
            this.msg = msg;
        }

        @Override
        public void run() {
            ThreadLocal threadLocal = new ThreadLocal();
            threadLocal.set(System.currentTimeMillis());
            rabbitTemplate.convertSendAndReceive(RabbitConfig.BUSINESS_ORDER_EXCHANGE, "", msg);
            //rabbitTemplate.convertAndSend(RabbitMQConfig.BUSINESS_ORDER_EXCHANGE, "", msg);
            log.info(Thread.currentThread().getName()+"耗时={}",System.currentTimeMillis() - (Long)threadLocal.get());
        }
    }

}
