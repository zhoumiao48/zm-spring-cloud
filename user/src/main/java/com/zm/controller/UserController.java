package com.zm.controller;

import com.zm.service.GoodsFeignClient;
import com.zm.util.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    GoodsFeignClient goodsFeignClient;
    @Resource
    private RestTemplate restTemplate;

    // 第一种方式
    /*@GetMapping("/getUser")
    public Object getMyGoods() {
        Object forObject = restTemplate.getForObject("http://localhost:8080/getGoods", Object.class);
        return ResponseResult.success("UserApplication调用GoodsApplication成功", forObject);
    }*/

    // 第二种调用方式：服务+接口直接调用goods中的服务：provider-goods（服务名），/getGoods（接口名）
   /* @GetMapping("/getMyGoods")
    public Object getMyGoods(){
        Object forObject = restTemplate.getForObject("http://provider-goods/getGoods", Object.class);
        return ResponseResult.success("UserApplication调用GoodsApplication成功", forObject);
    }*/

    // 第三种调用方式：使用Feign来进行调用（不用使用restTemplate来调用了）。
    @GetMapping("/getMyGoods")
    public Object getMyGoods() {
        Object forObject = goodsFeignClient.getGoods();
        return ResponseResult.success("通过Feign调用Goods服务成功", forObject);
    }


    @GetMapping("/getMyOrder")
    public Object getMyOrder() {
        Object forObject = restTemplate.getForObject("http://provider-order/getOrder", Object.class);
        return ResponseResult.success("UserApplication通过HTTP调用OrderApplication成功", forObject);
    }
}
