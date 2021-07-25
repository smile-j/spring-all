package org.dong.starter.config;

import org.dong.starter.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/17
 */
@Configuration
public class RabbitDemoConfig {


    /**
     *  声明callBackExchange
     *  direct类型
     */
    @Bean("callBackExchange")
    public DirectExchange callBackExchange(){
        return new DirectExchange("demo_callback_exchange");
    }


    /**
     * 声明callBackQueue
     */
    @Bean("callBackQueue")
    public Queue callBackQueue(){
        return new Queue("demo_callback_queue");
    }


    /**
     * 声明callBackQueue绑定关系
     */
    @Bean
    public Binding callBackQueueBinding(@Qualifier("callBackQueue") Queue queueB,
                                      @Qualifier("callBackExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).with("call-back-routing-key");
    }

    /**
     *  声明callBackExchange
     *  direct类型
     */
    @Bean("returnExchange")
    public DirectExchange returnExchange(){
        return new DirectExchange("demo_return_exchange");
    }


    /**
     * 声明returnQueue
     */
    @Bean("returnQueue")
    public Queue returnQueue(){
        return new Queue("demo_return_queue",true);
    }


    /**
     * 声明callBackQueueB绑定关系
     */
    @Bean
    public Binding returnBinding(@Qualifier("returnQueue") Queue queueB,
                                      @Qualifier("returnExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).with("return-routing-key");
    }
    //---------------------------ttl-------------------------------
    /**
     *  声明callBackExchange
     *  TopicExchange
     */
    @Bean("ttlExchange")
    public TopicExchange ttlExchange(){
        return new TopicExchange("test_ttl_exchange");
    }


    /**
     * 声明returnQueue
     */
    @Bean("ttlQueue")
    public Queue ttlQueue(){
        Map<String,Object> map = new HashMap<>(16);
//        map.put("x-dead-letter-exchange","receive_exchange");
//        map.put("x-dead-letter-routing-key", "receive_key");
        map.put("x-message-ttl",100000);//100秒
        return new Queue("test_ttl_queue", true,false,false,map);

    }


    /**
     * 声明callBackQueueB绑定关系
     */
    @Bean
    public Binding ttlBinding(@Qualifier("ttlQueue") Queue queue,
                                 @Qualifier("ttlExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("ttl.#");
    }

    //----------------------------死新队列-------------------------------------------------------

    /**
     *  声明callBackExchange
     *  TopicExchange
     */
    @Bean("testDlxExchange")
    public TopicExchange testDlxExchange(){
        return new TopicExchange("test_dlx_exchange");
    }


    /**
     * 声明testDtlQueue
     *  死信队列：
     *  1.过期 2.超过队列的长度，
     *  3. 消费者拒接消费消息，basicNack/basicReject,并且不把消息重新放入原目标队列,requeue=false
     *
     */
    @Bean("testDtlQueue")
    public Queue testDtlQueue(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("x-dead-letter-exchange","dlx_exchange");
        map.put("x-dead-letter-routing-key", "dtl.hehe");
        map.put("x-message-ttl",10000);//10秒
        map.put("x-max-length",10);//队列的长度10
        return new Queue("testDtlQueue", true,false,false,map);

    }


    /**
     * 声明callBackQueueB绑定关系
     */
    @Bean
    public Binding testDtlBinding(@Qualifier("testDtlQueue") Queue queue,
                              @Qualifier("testDlxExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("test.dtl.#");
    }

    /**
     *  声明callBackExchange
     *  TopicExchange
     */
    @Bean("dlxExchange")
    public TopicExchange dlxExchange(){
        return new TopicExchange("dlx_exchange");
    }


    /**
     * 声明returnQueue
     */
    @Bean("dtlQueue")
    public Queue dtlQueue(){
        Map<String,Object> map = new HashMap<>(16);
//        map.put("x-dead-letter-exchange","receive_exchange");
//        map.put("x-dead-letter-routing-key", "receive_key");
        return new Queue("dtlQueue", true,false,false,map);

    }


    /**
     * 声明callBackQueueB绑定关系
     */
    @Bean
    public Binding dtlBinding(@Qualifier("dtlQueue") Queue queue,
                              @Qualifier("dlxExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dtl.#");
    }
    //-----------------------------------延迟队列-------------------------------------------
    /**
     *  声明callBackExchange
     *  TopicExchange
     */
    @Bean("demoOrderExchange")
    public TopicExchange demoOrderExchange(){
        return new TopicExchange("demo_order_exchange");
    }


    /**
     * 声明testDtlQueue
     *  死信队列：
     *  1.过期 2.超过队列的长度，
     *  3. 消费者拒接消费消息，basicNack/basicReject,并且不把消息重新放入原目标队列,requeue=false
     *
     */
    @Bean("demoOrderQueue")
    public Queue demoOrderQueue(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("x-dead-letter-exchange","order_dlx_exchange");
        map.put("x-dead-letter-routing-key", "order.dtl.demo");
        map.put("x-message-ttl",10000);//10秒
//        map.put("x-max-length",10);//队列的长度10
        return new Queue("demoOrderQueue", true,false,false,map);

    }


    /**
     * 声明callBackQueueB绑定关系
     */
    @Bean
    public Binding demoOrderBinding(@Qualifier("demoOrderQueue") Queue queue,
                                  @Qualifier("demoOrderExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("test.order.#");
    }

    /**
     *  声明callBackExchange
     *  TopicExchange
     */
    @Bean("orderDlxExchange")
    public TopicExchange orderDlxExchange(){
        return new TopicExchange("order_dlx_exchange");
    }


    /**
     * 声明returnQueue
     */
    @Bean("orderDtlQueue")
    public Queue orderDtlQueue(){
        Map<String,Object> map = new HashMap<>(16);
//        map.put("x-dead-letter-exchange","receive_exchange");
//        map.put("x-dead-letter-routing-key", "receive_key");
        return new Queue("orderDtlQueue", true,false,false,map);

    }


    /**
     * 声明callBackQueueB绑定关系
     */
    @Bean
    public Binding orderDtlBinding(@Qualifier("orderDtlQueue") Queue queue,
                              @Qualifier("orderDlxExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("order.dtl.#");
    }
}
