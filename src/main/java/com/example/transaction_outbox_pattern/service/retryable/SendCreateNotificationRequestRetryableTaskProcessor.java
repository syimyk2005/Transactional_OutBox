package com.example.transaction_outbox_pattern.service.retryable;

import com.example.transaction_outbox_pattern.mapper.RetryableMapper;
import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import com.example.transaction_outbox_pattern.service.NotificationService;
import com.example.transaction_outbox_pattern.service.RetryableTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendCreateNotificationRequestRetryableTaskProcessor implements RetryableTaskProcessor{
    private final NotificationService notificationService;
    private final RetryableMapper retryableMapper;
    private final RetryableTaskService retryableTaskService;


    @Override
    public void processRetryableTasks(List<RetryableTask> retryableTasks) {
        List<RetryableTask> successRetryableTasks = new ArrayList<>();
        for (RetryableTask retryableTask : retryableTasks){

            var isSuccess = processRetryableTask(retryableTask);
            if (isSuccess){
                successRetryableTasks.add(retryableTask);
            }
        }
        retryableTaskService.markRetryableTAsksCompleted(successRetryableTasks);
    }

    private boolean processRetryableTask(RetryableTask retryableTask){
        var order = retryableMapper.convertJsonToOrder(retryableTask.getPayload());
        return notificationService.sendNotification(retryableTask.getId(), order.getCustomerEmail());
    }
}
