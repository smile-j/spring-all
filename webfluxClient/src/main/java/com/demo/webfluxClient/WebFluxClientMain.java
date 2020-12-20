package com.demo.webfluxClient;

import base.interfaces.ProxyCreator;
import base.interfaces.impl.JDKProxyCreator;
import com.demo.webfluxClient.service.IUserApi;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class WebFluxClientMain {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxClientMain.class, args);
    }


    @Bean
    ProxyCreator jdkProxy(){
        return new JDKProxyCreator();
    }

    @Bean
    FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator){
        return new FactoryBean<IUserApi>() {

            /**
             * 返回代理对象
             * @return
             * @throws Exception
             */
            @Override
            public IUserApi getObject() throws Exception {

                return (IUserApi) proxyCreator.createProxy(this.getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }
}
