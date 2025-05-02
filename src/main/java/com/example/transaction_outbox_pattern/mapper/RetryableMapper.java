package com.example.transaction_outbox_pattern.mapper;

import com.example.transaction_outbox_pattern.model.entity.Order;
import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.lang.reflect.Type;

@Mapper
public interface RetryableMapper {
    @Mapping(source = "order", target = "payload", qualifiedByName = "convertObjectToJson")
    RetryableTask toSendCreateDeliveryRequestRetryableTask(Order order);

    @Mapping(source = "order", target = "payload", qualifiedByName = "convertObjectToJson")
    RetryableTask toSendCreateNotificationRequestRetryableTask(Order order);

    @Named("convertObjectToJson")
    default String convertObjectToJson(Order order) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Order to JSON", e);
        }
    }

    @Named("convertObjectToOrder")
    default Order convertObjectToOrder(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to Order", e);
        }
    }
}

