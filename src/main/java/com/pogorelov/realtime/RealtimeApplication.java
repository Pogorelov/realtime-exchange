package com.pogorelov.realtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableScheduling
@EnableReactiveFeignClients
public class RealtimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtimeApplication.class, args);
    }

}
