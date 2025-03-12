package com.mypackage.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.mypackage.entity"})
public class ECommercePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommercePlatformApplication.class, args);
    }
}
