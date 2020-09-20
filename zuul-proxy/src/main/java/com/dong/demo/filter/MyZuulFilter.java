package com.dong.demo.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class MyZuulFilter extends ZuulFilter {

    /**
     * 类型包含 pre post route error
     * pre 代表在路由代理之前执行
     * route 代表代理的时候执行
     * error 代表出现错的时候执行
     * post 代表在route 或者是 error 执行完成后执行
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * filter 为链式过滤器，多个filter按顺序执行
     * javaee中根据filter先后配置顺序决定
     * zuul 根据order决定, 由小到大
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否启用该过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("MyZuulFilter execute!");
        return null;
    }
}