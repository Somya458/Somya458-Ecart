package com.notification.service.app.controller;

import com.notification.service.app.model.Notification;
import com.notification.service.app.service.EmailService;
import com.notification.service.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/send-notification")
	public ResponseEntity<?> sendNotification(@RequestBody Map<String, String> notificationRequest) {
		String email = Optional.ofNullable(notificationRequest.get("email"))
				.orElseThrow(() -> new IllegalArgumentException("Email is required"));
		String subject = Optional.ofNullable(notificationRequest.get("subject")).orElse("No Subject");
		String message = Optional.ofNullable(notificationRequest.get("message")).orElse("No Message");

		emailService.sendEmail(email, subject, message);

		Notification notification = new Notification(email, subject, message, "SENT");
		notification.setEmail(email);
		notification.setMessage(message);
		System.out.println("Reciever To Email  if is :" + email);
		notificationService.sendNotification(notification);

		return ResponseEntity.ok("Notification sent successfully.");
	}

	@GetMapping
	public List<Notification> getAllNotifications() {
		return notificationService.getAllNotifications();
	}

	@GetMapping("/{id}")
	public Notification getNotificationById(@PathVariable Long id) {
		return notificationService.getNotificationById(id);
	}
}
