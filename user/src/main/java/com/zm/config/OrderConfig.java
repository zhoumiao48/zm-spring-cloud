package com.zm.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.zm.rule.ZmRule1;
import org.springframework.context.annotation.Bean;

/**
 * @Description Order服务的负载均衡（轮询算法）
 * @Author zm
 * @Date 2020/9/15 0015 16:24
 **/
public class OrderConfig {
    @Bean
    public IRule iRule(){
        return new RoundRobinRule();
    }
}
