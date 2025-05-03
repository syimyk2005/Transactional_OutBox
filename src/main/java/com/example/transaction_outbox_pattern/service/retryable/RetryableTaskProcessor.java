package com.example.transaction_outbox_pattern.service.retryable;

import com.example.transaction_outbox_pattern.model.entity.RetryableTask;

import java.util.List;

public interface RetryableTaskProcessor {
    void processRetryableTasks(List<RetryableTask> retryableTasks);
}
