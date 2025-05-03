package com.example.transaction_outbox_pattern.controller;

import com.example.transaction_outbox_pattern.model.dto.CreateOrderDto;
import com.example.transaction_outbox_pattern.model.dto.OrderDto;
import com.example.transaction_outbox_pattern.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public OrderDto createOrder(@RequestBody CreateOrderDto dto) {
        log.info("Received request to create order: {}", dto);
        var response = orderService.createOrder(dto);
        log.info("Response from order service: {}", response);
        return response;
    }

}
