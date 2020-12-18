/*
package com.dong.demo.filter;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

*/
/**
 * 我们利用zuul作为路由去转发的时候，有时候比如程序内部错误或者是提供服务出现问题，都会返回给我们返回一个错误提示界面,例如500 404之类的报错页面，但是如果我们想自定义一个更友好化符合实际需求的信息需要怎么做呢？zuul提供了fallback机制，我们可以给断路配置一个自定义回退。
 *//*

@Component
public class ZuulFallBackApp implements FallbackProvider {
    @Override
    public String getRoute() {
        return "provider-user";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.BAD_REQUEST.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream((" happened error!"+getRoute()).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
*/
