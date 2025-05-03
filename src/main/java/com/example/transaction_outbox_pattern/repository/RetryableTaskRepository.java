package com.example.transaction_outbox_pattern.repository;

import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskStatus;
import com.example.transaction_outbox_pattern.model.enums.RetryableTaskType;
import com.example.transaction_outbox_pattern.service.RetryableTaskService;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface RetryableTaskRepository extends CrudRepository<RetryableTask, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r from RetryableTask r where r.type = :type " +  "" +
            "AND r.retryTime <= :retryTime " +
            "AND r.status= :status " +
            "order by r.retryTime asc ")
    List<RetryableTask> findRetryableTaskForProcessing(RetryableTaskType type, Instant retryTime, RetryableTaskStatus status, Pageable pageable);

    @Query("UPDATE RetryableTask r SET r.status= :status where r in :retryableTasks")
    void updateRetryableTasks(List<RetryableTask> retryableTasks, RetryableTaskStatus status);
}
