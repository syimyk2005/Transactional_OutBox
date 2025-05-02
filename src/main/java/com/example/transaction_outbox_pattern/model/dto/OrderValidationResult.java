package com.example.transaction_outbox_pattern.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderValidationResult {
    private UUID uuid;
    private Boolean isValid;
}
