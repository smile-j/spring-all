package org.dong.starter.test;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dong.starter.AmqpMain;
import org.dong.starter.enums.ExchangeEnum;
import org.dong.starter.enums.RouteEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/14
 */
@SpringBootTest(classes = AmqpMain.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestRabbitmq {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *延迟队列
     */
    @Test
    public void testDelay() throws InterruptedException {

        rabbitTemplate.convertAndSend("demo_order_exchange","test.order.routing","订单过期处理-"+simpleDateFormat.format(new Date()));
        for (int i=10;i>0;i--){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("----"+i);
        }


    }

    /**
     * 死信队列
     * 1.过期 2.超过队列的长度，
     * 3. 消费者拒接消费消息，basicNack/basicReject,并且不把消息重新放入原目标队列,requeue=false
     */
    @Test
    public void testDtl(){
        // 1.过期时间
//        rabbitTemplate.convertAndSend("test_dlx_exchange","test.dtl.routing","过期消息，私信队列？？？");
        //2.队列长度
//        for (int i=20;i>0;i--){
//            rabbitTemplate.convertAndSend("test_dlx_exchange","test.dtl.routing","过期消息，私信队列"+i);
//
//        }
        //3.消息拒收
        rabbitTemplate.convertAndSend("test_dlx_exchange","test.dtl.routing","消息拒收");

    }

    /**
     * 1.队列统一过期
     * 2.消息单独过期
     *
     * 如果设置了消息的过期时间  也设置了队列的过期时间  则以短的为准
     */
    @SneakyThrows
    @Test
    public void testTtl(){
        /**
         * 消息后处理对象，设置一些消息的参数信息
         */
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //1.设置message
                message.getMessageProperties().setExpiration("5000");//消息的过期时间
//                message.getMessageProperties().setHeader();
                //2.返回该消息
                return message;
            }
        };
        for(int i=10;i>0;i--){
//            rabbitTemplate.convertAndSend("test_ttl_exchange","ttl.routing","msg ttl-"+simpleDateFormat.format(new Date()));
            rabbitTemplate.convertAndSend("test_ttl_exchange", "ttl.routing", "msg ttl-" + simpleDateFormat.format(new Date())
                    , messagePostProcessor);
        }

    }


    @SneakyThrows
    @Test
    public void testAck(){
//        rabbitTemplate.convertAndSend(ExchangeEnum.DEMO_ACK_EXCHANGE.getName(), RouteEnum.DEMO_ACK_ROUTING_KEY.getName(),"msg ack");
        for(int i=10;i>0;i--){
            TimeUnit.SECONDS.sleep(1);
            rabbitTemplate.convertAndSend("demo_return_exchange","return-routing-key","msg ack-"+simpleDateFormat.format(new Date()));
        }

    }

    /**
     *回退模式:当消息发送给Exchange后，Exchange路由到Queue失败是才会执行ReturnCallBack步骤:
     * 1、开启退模式 publisher-returns="true"
     * 2．设置ReturncallBack
     * 3．设置Exchange处理消息的模式:
     *      1、如果消息没有路由到Queue，则丢弃消息（默认)
     *      2．如果消息没有路由到Queue，返回给消息发送方ReturncaLLBack
     */
    @Test
    public void testResturn(){
        //设置交换机处理失败消息的模式
//        rabbitTemplate.setMandatory(true);
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

        rabbitTemplate.convertAndSend("demo_return_exchange","return-routing-key--","return msg");

    }

    /**
     * 1．确认模式开启:connectionFactory中开启 publisher-confirms="true"
     * 2.在rabbitTempLate定义confirmcaLLBack回调函数T
     *
     */
    @Test
    public void testCallBack(){
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

        rabbitTemplate.convertAndSend("demo_callback_exchange","call-back-routing-key","confirm msg");

    }

//----------------------------------基本的---------------------------------------
    //topic 动态路由 订阅模式
    @Test
    public void testTopic(){

        rabbitTemplate.convertAndSend("topics","user","发送user路由");
        rabbitTemplate.convertAndSend("topics","user.save.info","发送user.save.info路由");
        rabbitTemplate.convertAndSend("topics","order.info","发送order.info路由");
    }

    //Route 路由
    @Test
    public void testRoute(){

        rabbitTemplate.convertAndSend("directs","info","发送info路由");
        rabbitTemplate.convertAndSend("directs","error","发送error路由");
    }

    //fanout 广播
    @Test
    public void testFanout(){

        for (int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("logs","","fanout 模型 "+i);

        }
    }

    //work
    @Test
    public void testWork(){

        for (int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("work","work 模型 "+i);

        }
    }


    /**
     * 需要有消费者才创建队列
     */
    @Test
    public void test(){
        rabbitTemplate.convertAndSend("hello","hello world");
//        rabbitTemplate.convertAndSend("demo_return_queue","hello world");
    }

}
