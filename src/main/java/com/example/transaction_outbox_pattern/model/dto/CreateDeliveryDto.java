package com.example.transaction_outbox_pattern.model.dto;

import lombok.Data;
import java.util.UUID;
@Data
public class CreateDeliveryDto {
    private UUID orderId;
    private String deliveryAddress;
}
