package com.example.netty.controller;

import com.example.netty.service.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author duguotao
 * @version 1.0.0
 * @since Created in 2022/1/24
 */
@RestController
@RequestMapping("/push")
public class TestController {

    @Autowired
    PushMsgService pushMsgService;

    @GetMapping("/{uid}")
    public void pushOne(@PathVariable String uid) {
        pushMsgService.pushMsgToOne(uid, "hello");
    }

}
