package com.zm.config;

import com.netflix.loadbalancer.IRule;
import com.zm.rule.ZmRule1;
import com.zm.rule.ZmRule2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Goods服务的负载均衡（自己定义的）
 * @Author zm
 * @Date 2020/9/15 0015 16:24
 **/
@Configuration
public class GoodsConfig {

    // 这里配置的rule是：如果两次都是同一实例，那么第三次一定是另一实例
    @Bean
    public IRule iRule(){
        return new ZmRule2();
    }
}
