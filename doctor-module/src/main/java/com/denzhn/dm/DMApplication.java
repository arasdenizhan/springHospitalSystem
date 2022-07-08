package com.denzhn.dm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("com.denzhn.repo.dm")
@EnableEurekaClient
@EnableFeignClients
public class DMApplication {

    public static void main(String[] args) {
        SpringApplication.run(DMApplication.class, args);
    }

}
