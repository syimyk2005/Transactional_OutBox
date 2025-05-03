package com.example.transaction_outbox_pattern.sheduller;

import com.example.transaction_outbox_pattern.mapper.RetryableMapper;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import com.example.transaction_outbox_pattern.service.DeliveryService;
import com.example.transaction_outbox_pattern.service.NotificationService;
import com.example.transaction_outbox_pattern.service.RetryableTaskService;
import com.example.transaction_outbox_pattern.service.retryable.RetryableTaskProcessor;
import com.example.transaction_outbox_pattern.service.retryable.SendCreateDeliveryRetryableTaskProcessor;
import com.example.transaction_outbox_pattern.service.retryable.SendCreateNotificationRequestRetryableTaskProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetryableTaskScheduler {

    private final RetryableTaskService retryableTaskService;
    private final RetryableMapper retryableMapper;
    private final DeliveryService deliveryService;
    private final NotificationService notificationService;
    private final Map<RetryableTaskType, RetryableTaskProcessor> taskProcessors = Map.of(
            RetryableTaskType.SEND_CREATE_DELIVERY_REQUEST, new SendCreateDeliveryRetryableTaskProcessor(deliveryService, retryableMapper, retryableTaskService),
            RetryableTaskType.SEND_CREATE_NOTIFICATION_REQUEST, new SendCreateNotificationRequestRetryableTaskProcessor(notificationService, retryableMapper, retryableTaskService)
    );


    @Scheduled(fixedRate = 5000)
    public void executeRetryableTask() {
        log.info("Starting retryable task processors");
        for (Map.Entry<RetryableTaskType, RetryableTaskProcessor> entry: taskProcessors.entrySet()){
            var taskType = entry.getKey();
            var taskProcessor = entry.getValue();

            var retryableTasks = retryableTaskService.getRetryableTaskForProcessing(taskType);
            taskProcessor.processRetryableTasks(retryableTasks);
        }
    }
}
