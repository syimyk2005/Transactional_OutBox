package com.example.transaction_outbox_pattern.mapper;

import com.example.transaction_outbox_pattern.model.dto.CreateOrderDto;
import com.example.transaction_outbox_pattern.model.dto.OrderDto;
import com.example.transaction_outbox_pattern.model.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Order toEntity(CreateOrderDto orderDto);
    OrderDto toDto(Order order);
}
