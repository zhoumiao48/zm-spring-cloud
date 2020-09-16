package com.zm;

import com.netflix.loadbalancer.IRule;
import com.zm.config.GoodsConfig;
import com.zm.config.OrderConfig;
import com.zm.rule.ZmRule1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
// 负载均衡算法配置方式2：
@RibbonClients({
        @RibbonClient(name = "provider-goods", configuration = GoodsConfig.class),
        @RibbonClient(name = "provider-order", configuration = OrderConfig.class),
})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    // 项目启动时创建RestTemplate类交由Spring来管理
    @Bean
    @LoadBalanced       //负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    // 负载均衡算法配置方式1：
   /* @Bean
    public IRule iRule(){
        return new ZmRule1();
    }*/
}