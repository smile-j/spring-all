package org.dong.starter.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/18
 */
@RestController
@Slf4j
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate ;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @GetMapping("/produce/{num}")
    public Integer produce(@PathVariable("num") int num) throws InterruptedException {
        for(int i=num;i>0;i--){
            TimeUnit.SECONDS.sleep(1);
            rabbitTemplate.convertAndSend("demo_return_exchange","return-routing-key",i+"-msg ack-"+simpleDateFormat.format(new Date()));
        }
        return num;
    }
    @PostConstruct
    public void postMethod(){
        log.info("-------------postMethod--------------------");
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.error("returnedMessage:[{}]", JSONUtil.toJsonStr(message));
                log.error("returnedMessage:[{}]", new String(message.getBody()));
                log.error("code:"+i);
                log.error("errorMsg:"+s);
                log.error("exchange:"+s1);
                log.error("routing-key:"+s2);

            }
        });

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             *
             * @param correlationData 相关配置信息
             * @param b ack exchange是否成功接受到消息 true 成功  false 失败
             * @param s 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                log.error("confirm方法执行");
                if(b){
                    log.error("接受成功："+s);
                }else {
                    log.error("接受失败："+s);
                }
            }
        });
    }
}
