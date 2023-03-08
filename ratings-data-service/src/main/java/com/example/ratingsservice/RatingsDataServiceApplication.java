package com.example.ratingsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class RatingsDataServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RatingsDataServiceApplication.class, args);
    }
}