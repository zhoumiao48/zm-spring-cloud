package com.zm.config;

import com.netflix.loadbalancer.IRule;
import com.zm.rule.ZmRule1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Goods服务的负载均衡（自己定义的）
 * @Author zm
 * @Date 2020/9/15 0015 16:24
 **/
@Configuration
public class GoodsConfig {

    // 负载均衡算法切换配置（这里配置的是RandomRule算法）
    @Bean
    public IRule iRule(){
        return new ZmRule1();
    }
}
