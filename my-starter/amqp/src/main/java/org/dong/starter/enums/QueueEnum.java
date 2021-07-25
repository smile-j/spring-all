package org.dong.starter.enums;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dongjunpeng
 * @Description  交换机、业务队列、路由标识符 常量定义
 * @date 2021/7/17
 */
public enum QueueEnum implements BaseEnum{

    /*队列*/
    DEMO_QUEUE_ACK("demo-queue-ack","ack"),
    BUSINESS_QUEUE_A("business-queue-a","业务A队列"),
    BUSINESS_QUEUE_B("business-queue-b","业务B队列"),
    DEAD_LETTER_QUEUE_A("dead-letter-queue-a","业务A死信队列名称"),
    DEAD_LETTER_QUEUE_B("dead-letter-queue-b","业务B死信队列名称");


    private QueueEnum(String name,String desc){
        this.name = name;
        this.desc = desc;
    }
    private final String name;
    private final String desc;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public String getDesc(String queueName){
        for (QueueEnum e:QueueEnum.values()){
            if(e.getName().equals(queueName)){
                return e.getDesc();
            }
        }
        return null;
    }
}
