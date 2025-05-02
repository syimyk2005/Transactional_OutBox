package com.example.transaction_outbox_pattern.model.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class DeliveryDto {
    private UUID deliveryId;
    private String status;
}

