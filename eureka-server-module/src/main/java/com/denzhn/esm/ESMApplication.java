package com.denzhn.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ESMApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESMApplication.class, args);
    }

}
