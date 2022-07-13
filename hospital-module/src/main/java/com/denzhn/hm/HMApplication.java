package com.denzhn.hm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("com.denzhn.repo.hm")
@EnableEurekaClient
@EnableFeignClients
public class HMApplication {

    public static void main(String[] args) {
        SpringApplication.run(HMApplication.class, args);
    }

}
