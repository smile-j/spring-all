package com.dong.demo.webflux.routers;


import com.dong.demo.webflux.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


/**
 *全局路由配置类
 *
 */
@Configuration
public class AllRouter {


    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        System.out.println("-------------------");
        return
                // 统一添加user前缀，相当于@RequestMapping("user")
                RouterFunctions.nest(RequestPredicates.path("/router"),
                        // 查询所有用户
                        RouterFunctions.route(RequestPredicates.GET("/"), userHandler::getAllUser)
                                // 新增用户
                        .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),                                           userHandler::createUser)
                                // 根据id删除用户
                        .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::delUser));
    }

/*    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler){

       return  RouterFunctions.nest(
                //统一添加user前缀 @RequestMapping("/user")
                RequestPredicates.path("/user"),
                //    @GetMapping("/all")
                RouterFunctions.route(RequestPredicates.GET("/all"), handler::getAllUser)
               .andRoute(RequestPredicates.POST("/createUser").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::createUser)
               .andRoute(RequestPredicates.DELETE("/{id}"),handler::delUser)
        );

    }*/


}
