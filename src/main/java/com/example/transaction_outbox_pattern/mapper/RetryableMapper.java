package com.example.transaction_outbox_pattern.mapper;

import com.example.transaction_outbox_pattern.model.entity.Order;
import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface RetryableMapper {
    @Mapping(source = "order", target = "payload", qualifiedByName = "convertObjectToJson")
    RetryableTask toRetryableTask(Order order, RetryableTaskType retryableTaskType);

    @Named("convertObjectToJson")
    default String convertObjectToJson(Order order) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Order to JSON", e);
        }
    }

    @Named("convertJsonToOrder")
    default Order convertJsonToOrder(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to Order", e);
        }
    }
}

