package com.example.transaction_outbox_pattern.model.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public class OrderValidationResult {
    private UUID uuid;
    private Boolean isValid;
}
