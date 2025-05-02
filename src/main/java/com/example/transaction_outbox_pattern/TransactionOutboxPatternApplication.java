package com.example.transaction_outbox_pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.transaction_outbox_pattern.client")

public class TransactionOutboxPatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionOutboxPatternApplication.class, args);
    }

}
