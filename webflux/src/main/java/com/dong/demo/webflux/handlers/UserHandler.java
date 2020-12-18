package com.dong.demo.webflux.handlers;

import com.dong.demo.webflux.dao.UserRepository;
import com.dong.demo.webflux.entity.User;
import com.dong.demo.webflux.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserHandler {

    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 所有的用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request){
        log.info("-----getAllUser------");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.repository.findAll(), User.class);
    }

    /**
     * 创建用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request){
        log.info("-----createUser------");

        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(user -> {
            // 校验用户名字合法性
            CheckUtil.checkName(user.getName());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(this.repository.save(user), User.class);
        });

    }

    /**
     * 删除
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> delUser(ServerRequest request){
        log.info("-----delUser------");
        String id = request.pathVariable("id");

        return this.repository.findById(id)
                .flatMap(user -> this.repository.delete(user))
                .then(ServerResponse.ok().build())
                .switchIfEmpty(ServerResponse.notFound().build());

    }


}
