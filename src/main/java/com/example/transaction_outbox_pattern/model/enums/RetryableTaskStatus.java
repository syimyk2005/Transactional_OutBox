package com.example.transaction_outbox_pattern.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RetryableTaskStatus {
    IN_PROGRESS("IN PROGRESS"),
    SUCCESS("SUCCESS");
    private String value;

    public static RetryableTaskStatus fromValue(String value) {
        for (RetryableTaskStatus status : RetryableTaskStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}


