/*
package com.dong.demo.jdk8.stream;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

*/
/**
 *
 * Processor,需要继承SubmissionPublisher并实现Process接口
 *
 * 输入源数据integer,过滤掉小于0的，然后转换成字符串发布出去
 *
 *//*

class MyProccessor extends SubmissionPublisher<String> implements Flow.Processor<Integer,String>{

    private Flow.Subscription subscription ;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        //接送到一个数据，处理
        System.out.println("接收到数据："+item);
        if(item>0){
            this.submit("转换后的数据："+item);
        }
        //处理完整调用request再请求一个数据
        this.subscription.request(1);
        //或者已经达到了目标，调用cancel告诉发布者不再接受数据
        this.subscription.cancel();
    }

    @Override
    public void onError(Throwable throwable) {

        //出现了异常（例如处理数据的时候产生了异常）
        throwable.printStackTrace();

        //告诉发布者，后面不接受数据了
        this.subscription.cancel();

    }

    @Override
    public void onComplete() {

        //全部数据处理完了（发布者关闭了）
        System.out.println("处理完了");
    }
}

public class FlowDemo2 {


    public static void main(String[] args) throws InterruptedException {


        //1.定义发布者，发布的数据类型是Integer
        //直接使用jdk自动的SubmissionPublisher
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //2.定义处理器，对数据进行过滤
        MyProccessor proccessor = new MyProccessor();

        //3.发布者和处理器 建立订阅关系
        publisher.subscribe(proccessor);

        //4.定义最终订阅者，消费String类型数据
        Flow.Subscriber subscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription ;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //保存订阅关系，需要用它来给发布者相应
                this.subscription = subscription;

                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {


                //接送到一个数据，处理
                System.out.println("接收到数据："+item);


                //处理完整调用request再请求一个数据
                this.subscription.request(1);
                //或者已经达到了目标，调用cancel告诉发布者不再接受数据
//                this.subscription.cancel();

            }


            @Override
            public void onError(Throwable throwable) {

                //出现了异常（例如处理数据的时候产生了异常）
                throwable.printStackTrace();

                //告诉发布者，后面不接受数据了
                this.subscription.cancel();

            }

            @Override
            public void onComplete() {

                //全部数据处理完了（发布者关闭了）
                System.out.println("处理完了");
            }
        };
        //5.处理器和最终订阅者  建立关系
        proccessor.subscribe(subscriber);

        //6.生产数据，并发布
        publisher.submit(-111);
        publisher.submit(111);

        publisher.close();
        Thread.currentThread().join(1000);

    }
}
*/
