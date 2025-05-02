package com.example.transaction_outbox_pattern.model.entity;

import com.example.transaction_outbox_pattern.model.enums.RetryableTaskStatus;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import com.example.transaction_outbox_pattern.util.RetryableTaskStatusConverter;
import com.example.transaction_outbox_pattern.util.RetryableTaskTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.Instant;

/**
 * Задача, требующая повторного выполнения
 */
@Entity
@Getter
@Setter
public class RetryableTask extends BaseEntity {
    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String payload;

    @Convert(converter = RetryableTaskTypeConverter.class)
    private RetryableTaskType type;

    @Convert(converter = RetryableTaskStatusConverter.class)
    private RetryableTaskStatus status;

    private Instant retryTime;
}


