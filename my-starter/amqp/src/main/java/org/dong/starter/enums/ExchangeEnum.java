package org.dong.starter.enums;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/18
 */
public enum ExchangeEnum implements BaseEnum{

    /*交换机*/
    DEMO_ACK_EXCHANGE("demo-ack-exchange",""),
    BUSINESS_EXCHANGE("business-exchange","业务交换机标识符"),
    DEAD_LETTER_EXCHANGE("dead-letter-exchange","业务死信交换机标识符"),
    ORDER_DEAD_LETTER_EXCHANGE("x-dead-letter-exchange","订单死信队列交换机标识符");
    private ExchangeEnum(String name,String desc){
        this.name = name;
        this.desc = desc;
    }
    private final String name;
    private final String desc;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
