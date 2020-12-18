/*
package com.dong.demo.jdk8.stream;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {

    public static void main(String[] args) throws InterruptedException {

        //1.定义发布者，发布的数据类型是Integer
        //直接使用jdk自带的SubmissionPublisher，它实现了Publisher接口
        SubmissionPublisher<Integer> publisher= new SubmissionPublisher();

        //2.定义订阅者
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
        };

        //3.发布者和订阅者 建立订阅关系
        publisher.subscribe(subscriber);

        //4.生产数据，并发布
        //这里忽略数据生产过程
        int data =111;
        publisher.submit(data);

        //5.结束后 关闭发布者
        //
        publisher.close();

        Thread.currentThread().join(1000);
    }

}
*/
