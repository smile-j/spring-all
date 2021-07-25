package org.dong.starter.producter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/18
 */
@RestController
public class ProducterContoller {


    @GetMapping("/produce/{num}")
    public Integer produce(@PathVariable("num") int num){
        return num;
    }

}
