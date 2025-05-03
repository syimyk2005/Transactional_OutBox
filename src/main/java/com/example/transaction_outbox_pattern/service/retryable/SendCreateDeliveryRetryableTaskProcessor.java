package com.example.transaction_outbox_pattern.service.retryable;

import com.example.transaction_outbox_pattern.mapper.RetryableMapper;
import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import com.example.transaction_outbox_pattern.service.DeliveryService;
import com.example.transaction_outbox_pattern.service.RetryableTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Log4j
@Service
public class SendCreateDeliveryRetryableTaskProcessor implements RetryableTaskProcessor{
    private final DeliveryService deliveryService;
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
        return deliveryService.processDelivery(retryableTask.getId(), order.getDeliveryAddress());
    }
}
