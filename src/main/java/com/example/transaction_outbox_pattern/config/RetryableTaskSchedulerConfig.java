package com.example.transaction_outbox_pattern.config;

import com.example.transaction_outbox_pattern.service.RetryableTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class RetryableTaskSchedulerConfig {

    private final RetryableTaskService retryableTaskService;

    @Scheduled(fixedRate = 5000)
    public void executeRetryableTask(){

    }
}
