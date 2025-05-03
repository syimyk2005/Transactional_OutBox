package com.example.transaction_outbox_pattern.service;

import com.example.transaction_outbox_pattern.mapper.OrderMapper;
import com.example.transaction_outbox_pattern.model.dto.CreateOrderDto;
import com.example.transaction_outbox_pattern.model.dto.OrderDto;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import com.example.transaction_outbox_pattern.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RetryableTaskService retryableService;

    @Transactional
    public OrderDto createOrder(CreateOrderDto dto) {
        var order = orderRepository.save(
                orderMapper.toEntity(dto)
        );
        retryableService.createRetryableTask(order, RetryableTaskType.SEND_CREATE_DELIVERY_REQUEST);
        retryableService.createRetryableTask(order, RetryableTaskType.SEND_CREATE_NOTIFICATION_REQUEST);

        return orderMapper.toDto(order);
    }
}
