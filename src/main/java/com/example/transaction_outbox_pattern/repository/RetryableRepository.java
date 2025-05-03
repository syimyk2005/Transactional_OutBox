package com.example.transaction_outbox_pattern.repository;

import com.example.transaction_outbox_pattern.model.entity.RetryableTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RetryableRepository extends CrudRepository<RetryableTask, UUID> {}