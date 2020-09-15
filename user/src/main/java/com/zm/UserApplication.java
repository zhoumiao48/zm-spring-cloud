package com.zm;

import com.netflix.loadbalancer.IRule;
import com.zm.rule.ZmRule1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
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

    // 负载均衡算法切换配置（这里配置的是RandomRule算法）
    @Bean
    public IRule iRule(){
        return new ZmRule1();
    }
}