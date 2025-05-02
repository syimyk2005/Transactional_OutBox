package com.example.transaction_outbox_pattern.model.dto;

import java.util.List;
import java.util.UUID;

public class OrderDto {

    private UUID uuid;
    private Long customerId;
    private List<Long> productIds;
    private Integer quantity;
    private String deliveryAddress;
    private String paymentMethod;
    private String orderNotes;
}
