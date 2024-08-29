package com.order.api.app.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8007")
public interface NotificationClient {
	
    @PostMapping("/notifications/send-notification")
    void sendOrderPlacedNotification(@RequestBody Map<String, String> notificationRequest);
}


