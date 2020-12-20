package base.interfaces.impl;

import base.annotations.ApiServer;
import base.beans.MethodInfo;
import base.beans.ServerInfo;
import base.interfaces.ProxyCreator;
import base.interfaces.RestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class JDKProxyCreator implements ProxyCreator {
    @Override
    public Object createProxy(Class<?> type) {
        log.info("---createProxy---"+type);
        //根据接口得到api服务器信息
        ServerInfo serverInfo = extractServerInfo(type);

        log.info("serverInfo:{}",serverInfo);

        //给每一个代理类一个实现
        RestHandler handler = new WebClientRestHandler();

        //初始化服务器的信息（初始化webclient）
        handler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //根据方法和参数得到嗲用信息
                MethodInfo methodInfo = extractMethodInfo(method,args);

                //调用rest
                log.info("methodInfo :{}", methodInfo);
                return handler.invokeRest(methodInfo);
            }
        });
    }

    private MethodInfo extractMethodInfo(Method method, Object[] args) {
        MethodInfo methodInfo = new MethodInfo();
        extractUrlAndMethod(method, methodInfo);
        extractRequestParmAndBody(method, args, methodInfo);

        //提取返回对象信息
        extractReturnInfo(method,methodInfo);

        return methodInfo;
    }

    private void extractReturnInfo(Method method, MethodInfo methodInfo) {

        //返回是flux还是mono
        //isAssignableFrom 判断类型是否某个字类
        //instanceof 判断实例是否属于某个字类
        boolean isFlux = method.getReturnType().isAssignableFrom(Flux.class);
        methodInfo.setReturnFlux(isFlux);

        //得到返回对象的实际类型
        Class<?> elementType = extractElementType(method.getGenericReturnType());

        methodInfo.setReturnElementType(elementType);

    }

    private Class<?> extractElementType(Type genericReturnType) {
        Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();

        return (Class<?>) actualTypeArguments[0];
    }

    private void extractRequestParmAndBody(Method method, Object[] args, MethodInfo methodInfo) {
        //得到调用的参数和body
        Parameter[] parameters = method.getParameters();
        Map<String,Object> params  = new LinkedHashMap<>();
        methodInfo.setParams(params);

        for (int i=0;i<parameters.length;i++){

            //是否带@PathVariable
            PathVariable annoPath = parameters[i].getAnnotation(PathVariable.class);

            if(annoPath != null){
                params.put(annoPath.value(),args[i]);
            }

            //@ReqestBody

            RequestBody annoRequestBody= parameters[i].getAnnotation(RequestBody.class);

            if(annoRequestBody != null){
                methodInfo.setBody((Mono<?>) args[i]);

                //请求对象的实际类型
                methodInfo.setBodyElementType(extractElementType(parameters[i].getParameterizedType()));
            }


        }
    }

    private void extractUrlAndMethod(Method method, MethodInfo methodInfo) {
        //得到请求URL和请求方法
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation:annotations){
            //Get
            if(annotation instanceof GetMapping){
                GetMapping a= (GetMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.GET);
            }
            //POST
            else if(annotation instanceof PostMapping){
                PostMapping a= (PostMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.POST);
            }
            //DELETE
            else if(annotation instanceof DeleteMapping){
                DeleteMapping a= (DeleteMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.DELETE);
            }
            //PUT
            else if(annotation instanceof PutMapping){
                PutMapping a= (PutMapping) annotation;
                methodInfo.setUrl(a.value()[0]);
                methodInfo.setMethod(HttpMethod.PUT);
            }
        }
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();
        ApiServer annoatation = type.getAnnotation(ApiServer.class);
        serverInfo.setUrl(annoatation.value());
        return serverInfo;
    }
}
