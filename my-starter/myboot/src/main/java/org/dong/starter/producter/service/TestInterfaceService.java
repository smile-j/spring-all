package org.dong.starter.producter.service;

import org.dong.starter.producter.controller.TestInterface;
import org.dong.starter.vo.ReqVo;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class TestInterfaceService implements TestInterface {
    @Override
    public Object testStr() {
        return "testStr";
    }

    @Override
    public Object testPost(ReqVo reqVo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "success";
    }
}
