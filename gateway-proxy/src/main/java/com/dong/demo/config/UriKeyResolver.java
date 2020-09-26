package com.dong.demo.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class UriKeyResolver implements KeyResolver {

    @Override
      public Mono<String> resolve(ServerWebExchange exchange) {
        //        return Mono.just(exchange.getRequest().getURI().getPath());
                String user = "1";
                return Mono.just(user);
            }

}
