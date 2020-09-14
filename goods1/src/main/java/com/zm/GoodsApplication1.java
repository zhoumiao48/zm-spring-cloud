package com.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GoodsApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication1.class, args);
    }
}
