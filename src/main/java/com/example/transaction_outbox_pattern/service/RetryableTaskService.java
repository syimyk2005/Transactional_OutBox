package com.example.transaction_outbox_pattern.service;

import com.example.transaction_outbox_pattern.mapper.RetryableMapper;
import com.example.transaction_outbox_pattern.model.entity.Order;
import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskStatus;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import com.example.transaction_outbox_pattern.repository.RetryableRepository;
import com.example.transaction_outbox_pattern.repository.RetryableTaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RetryableTaskService {
    private final RetryableRepository retryableRepository;
    private final RetryableMapper retryableTaskMapper;
    private final RetryableTaskRepository retryableTaskRepository;
    @Value("${retryableTask.limit}")
    private Integer limit;
    @Value("${retryableTask.timeoutInSeconds}")
    private Integer timeoutInSeconds;


    @Transactional
    public RetryableTask createRetryableTask(Order order, RetryableTaskType type){
        RetryableTask retryableTask = retryableTaskMapper.toRetryableTask(order, type);
        return retryableRepository.save(retryableTask);
    }

    @Transactional
    public List<RetryableTask> getRetryableTaskForProcessing(RetryableTaskType type){
        Pageable pageable = (Pageable) PageRequest.of(0, limit);
        List<RetryableTask> retryableTasks = retryableTaskRepository.findRetryableTaskForProcessing(
                type, Instant.now(), RetryableTaskStatus.IN_PROGRESS, pageable);

        for (RetryableTask retryableTask: retryableTasks) {
            retryableTask.setRetryTime(Instant.now().plus(Duration.ofSeconds(timeoutInSeconds)));
        }
        return retryableTasks;
    }

    @Transactional
    public void markRetryableTAsksCompleted(List<RetryableTask> retryableTasks){
        for (RetryableTask retryableTask : retryableTasks) {
            retryableTask.setStatus(RetryableTaskStatus.SUCCESS);
        }
        retryableTaskRepository.saveAll(retryableTasks);
    }

}
