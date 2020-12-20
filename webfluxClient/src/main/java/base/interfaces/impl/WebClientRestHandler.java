package base.interfaces.impl;

import base.beans.MethodInfo;
import base.beans.ServerInfo;
import base.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientRestHandler implements RestHandler {

    private WebClient webClient;

    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    /**
     * 处理rest请求
     * @param methodInfo
     * @return
     */
    @Override
    public Object invokeRest(MethodInfo methodInfo) {

        Object result = null;
        WebClient.RequestBodySpec request = this.webClient
                //请求方法
                .method(methodInfo.getMethod())
                //请求url
//                .uri(methodInfo.getUrl())
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                //
                .accept(MediaType.APPLICATION_JSON);
        //发出请求
//                .retrieve();
        WebClient.ResponseSpec retrieve = null;
        //判断是否带了body
        if (methodInfo.getBody()!=null){
            //发出请求
            retrieve = request.body(methodInfo.getBody(),methodInfo.getBodyElementType()).retrieve();
        }else {
            retrieve = request.retrieve();
        }

        //处理异常
        retrieve.onStatus(status->status.value()==404,
                response-> Mono.just(new RuntimeException("Not Found")));

        //处理body
        if(methodInfo.isReturnFlux()){
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        }else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }
        return result;
    }


}
