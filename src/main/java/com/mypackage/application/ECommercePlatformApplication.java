package com.mypackage.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.mypackage.repository")
@ComponentScan("com.mypackage")
@EntityScan(basePackages = {"com.mypackage"})
public class ECommercePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommercePlatformApplication.class, args);
    }
}
