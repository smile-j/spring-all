package org.dong.starter.test.rabbit.workQueue;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import org.dong.starter.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author dongjunpeng
 * @Description  平均消费
 * @date 2021/7/12
 */
public class Consumer {

    public static void main(String[] args) throws IOException, InterruptedException {

       for (int i=0;i<2;i++){
           final int ii = i;
           Thread t = new Thread(()->{
               try {
                   Connection connection = RabbitMqUtil.getConnection();
                   Channel channel = connection.createChannel();

                   channel.queueDeclare("work",true,false,false,null);
                   channel.basicConsume("work",true,new DefaultConsumer(channel){
                       @SneakyThrows
                       @Override
                       public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                           String msg = new String(body);
                           System.out.println("消费者-"+ii+msg);
                           if(ii==0){
                               TimeUnit.SECONDS.sleep(2);
                           }
                           if("11".equals(msg)){
                               System.out.println("=================");
                               throw new RuntimeException(Thread.currentThread().getName()+"人工抛出异常"+ii);
                           }
                       }
                   });
               }catch (IOException e){
                   e.printStackTrace();
               }

           },"线程-"+i);
           t.start();
           t.join();
       }



    }

}
