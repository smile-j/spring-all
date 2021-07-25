package org.dong.starter.producter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dong.starter.DateUtil;
import org.dong.starter.vo.ReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private DateUtil dateUtil;

    @GetMapping("testGet")
    public Object testUtil(){
        return "hello!=="+dateUtil.getLocalTime();
    }

    @PostMapping
    public Object testPost() {
        return testPost();
    }

    @PostMapping("postValid")
    public Object testPost( @Validated @RequestBody(required = true) ReqVo reqVo, BindingResult bindingResult) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        log.info("-----"+mapper.writeValueAsString(reqVo));
        return "success";
    }
    @PostMapping("postValid2")
    public Object testPost(@Valid @RequestBody(required = true) List<String> adds, BindingResult bindingResult) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        log.info("-----"+mapper.writeValueAsString(adds));
        return "success";
    }
}
