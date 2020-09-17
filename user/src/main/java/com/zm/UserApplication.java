package com.zm;

import com.netflix.loadbalancer.IRule;
import com.zm.config.GoodsConfig;
import com.zm.config.OrderConfig;
import com.zm.rule.ZmRule1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RibbonClients({    // 负载均衡算法配置方式2：给goods服务配置自定义的负载均衡算法，给order服务配置自带的轮训算法
        @RibbonClient(name = "provider-goods", configuration = GoodsConfig.class),
        @RibbonClient(name = "provider-order", configuration = OrderConfig.class),
})
@EnableFeignClients
@EnableHystrix
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

    /* 负载均衡算法配置方式1： 如果负载均衡配置的跟UserApplication在同一个目录下，那这个均衡算法对
     所有的服务全部生效 */
   /* @Bean
    public IRule iRule(){
        return new ZmRule1();
    }*/
}