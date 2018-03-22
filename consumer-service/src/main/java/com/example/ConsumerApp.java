package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }
}

// todo log format string, String.format ('%s...')
// todo RestTemplate uri pattern (//greetings-service/hi/{name})
// todo git push client side load balance with Ribbon
// todo how long after a service is down, registry server (Eureka Server) will get noticed?
// todo Eureka Server clustering?