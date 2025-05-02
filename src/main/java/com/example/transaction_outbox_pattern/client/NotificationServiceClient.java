package com.example.transaction_outbox_pattern.client;

import com.example.transaction_outbox_pattern.model.dto.CreateNotificationDto;
import com.example.transaction_outbox_pattern.model.dto.NotificationDto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "notification-service", url = "${integration.notificationService.url}")
public interface NotificationServiceClient {
    @PostMapping("/notifications")
    NotificationDto createNotification(@RequestBody CreateNotificationDto dto);
}
