package com.zm.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/9/17 0017 9:15
 **/
@FeignClient(name = "provider-goods")       // 标明是哪一个微服务（微服务的名称）
public interface GoodsFeignClient {
    @RequestMapping("/getGoods")
    public Object getGoods();
}
