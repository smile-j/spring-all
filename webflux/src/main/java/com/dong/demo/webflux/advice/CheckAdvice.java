package com.dong.demo.webflux.advice;


import com.dong.demo.webflux.exceptions.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

/**
 *
 * 参数异常处理
 *
 *
 */

@ControllerAdvice
public class CheckAdvice {


    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity handleBindException(WebExchangeBindException e){
        String str = toStr(e);
        System.out.println("----> "+str);
        return new ResponseEntity<String>(str, HttpStatus.BAD_REQUEST);
    }

    /**
     * 把校验异常转换为字符串
     * @param ex
     * @return
     */
    private String toStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
                .map(e->e.getField()+":"+e.getDefaultMessage())
                .reduce("",(s1,s2)->s1+"\n"+s2);
    }


    @ExceptionHandler(CheckException.class)
    public ResponseEntity handleBindException(CheckException e){
        String str = toStr2(e);
        System.out.println("----> "+str);
        return new ResponseEntity<String>(str, HttpStatus.BAD_REQUEST);
    }

    private String toStr2(CheckException ex) {
        return ex.getFiledName()+":"+ex.getFiledValue();
    }


    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ResponseBody
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public String asyncRequestTimeoutHandler(AsyncRequestTimeoutException e) {
        System.out.println("异步请求超时");
        return "304";
    }

}
