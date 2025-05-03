package com.example.transaction_outbox_pattern.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {

    private Long customerId;
    private List<Long> productIds;
    private String deliveryAddress;
    private String paymentMethod;
    private String orderNotes;
 }
