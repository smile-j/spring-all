package com.dong.demo.webflux.handlers;

import com.dong.demo.webflux.exceptions.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @description: routerFunction 异常处理类
 */
@Component
@Order(-2) //优先级 越小越先执行，最少得调到-2
public class ExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        //设置响应头400
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        //设置返回类型
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        //异常信息
        String result = toStr(ex);

        DataBuffer db = response.bufferFactory().wrap(result.getBytes());
        return response.writeWith(Mono.just(db));
    }

    private String toStr(Throwable ex) {
        //已知异常
        if (ex instanceof CheckException) {
            CheckException checkException = (CheckException) ex;
            return checkException.getFiledName() + ": Invalided " + checkException.getFiledValue();
        }
        //未知异常
        else {
            ex.printStackTrace();
            return ex.toString();
        }
    }
}
