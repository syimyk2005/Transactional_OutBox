package com.example.transaction_outbox_pattern.client;

import com.example.transaction_outbox_pattern.model.dto.CreateDeliveryDto;
import com.example.transaction_outbox_pattern.model.dto.DeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-service", url = "${integration.deliveryService.url}")
public interface DeliveryServiceClient {
    @PostMapping("/deliveries")
    DeliveryDto createDelivery(@RequestBody CreateDeliveryDto deliveryRequest);
}
