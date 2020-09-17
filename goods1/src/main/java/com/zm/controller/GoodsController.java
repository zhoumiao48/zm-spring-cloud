package com.zm.controller;

import com.zm.util.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @GetMapping("/getGoods")
    public Object getGoods(){
        // 人为制造一个异常出来，用于Hystrix的测试
        System.out.println(1/0);
        return ResponseResult.success("i'm a goods information from GoodsApplication1");
    }
}
