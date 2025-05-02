package com.example.transaction_outbox_pattern.util;

import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import jakarta.persistence.AttributeConverter;


public class RetryableTaskTypeConverter implements AttributeConverter<RetryableTaskType, String> {

    @Override
    public String convertToDatabaseColumn(RetryableTaskType status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public RetryableTaskType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return RetryableTaskType.fromValue(dbData);
    }
}
