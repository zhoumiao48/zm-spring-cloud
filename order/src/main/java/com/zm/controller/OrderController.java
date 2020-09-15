package com.zm.controller;

import com.zm.util.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/getOrder")
    public Object getOrder(){
        return ResponseResult.success("i'm a order information from com.zm.OrderApplication");
    }
}
