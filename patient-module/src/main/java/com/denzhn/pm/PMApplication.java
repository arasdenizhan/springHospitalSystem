package com.denzhn.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan("com.denzhn.repo.dm")
@EnableEurekaClient
public class PMApplication {

    public static void main(String[] args) {
        SpringApplication.run(PMApplication.class, args);
    }

}
