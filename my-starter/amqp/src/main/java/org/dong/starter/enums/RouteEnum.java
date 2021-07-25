package org.dong.starter.enums;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/18
 */
public enum RouteEnum implements BaseEnum {

    /*路由标识符*/
    DEMO_ACK_ROUTING_KEY("demo-ack-routing-key","demo-key"),
    DEAD_LETTER_QUEUE_A_ROUTING_KEY("dead-letter-queue-a-routing-key","业务A队列与死信队列交换机绑定键"),
    DEAD_LETTER_QUEUE_B_ROUTING_KEY("dead-letter-queue-b-routing-key","业务B队列与死信队列交换机绑定键"),
    ORDER_DEAD_LETTER_ROUTING_KEY("x-routing-key","订单死信队列交换机绑定键");
    private RouteEnum(String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    public static String getDesc(String name){
        for(RouteEnum e:RouteEnum.values()){
            if(e.getName().equals(name)){
                return e.getDesc();
            }
        }
        return null;
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
