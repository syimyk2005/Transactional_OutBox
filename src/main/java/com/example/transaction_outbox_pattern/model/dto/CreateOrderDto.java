package com.example.transaction_outbox_pattern.model.dto;

import java.util.List;

public class CreateOrderDto {

    private Long customerId;
    private List<Long> productIds;
    private String deliveryAddress;
    private String paymentMethod;
    private String orderNotes;
 }
