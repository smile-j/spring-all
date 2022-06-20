package com.example.netty.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author duguotao
 * @version 1.0.0
 * @since Created in 2022/1/24
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(RuntimeException ex) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 4006);
        map.put("msg", ex.getMessage());
        map.put("data", null);
        return map;
    }

}
