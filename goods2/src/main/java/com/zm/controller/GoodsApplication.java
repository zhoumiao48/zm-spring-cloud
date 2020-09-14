package com.zm.controller;

import com.zm.util.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsApplication {

    @GetMapping("/getGoods")
    public Object getGoods(){
        return ResponseResult.success("i'm a goods information from GoodsApplication2");
    }
}
