package com.example.transaction_outbox_pattern.service;

import com.example.transaction_outbox_pattern.mapper.RetryableMapper;
import com.example.transaction_outbox_pattern.model.entity.Order;
import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import com.example.transaction_outbox_pattern.repository.RetryableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RetryableService {
    private final RetryableRepository retryableRepository;
    private final RetryableMapper RetryableTaskMapper;

//    public RetryableTask createRetryableTask(Order order, RetryableTaskType type){

  //  }

}
