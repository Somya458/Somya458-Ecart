package com.notification.service.app.service;

import com.notification.service.app.model.Notification;
import com.notification.service.app.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

	 @Autowired
	    private NotificationRepository notificationRepository;

	    public Notification sendNotification(Notification notification) {
	        return notificationRepository.save(notification); 
	    }

	    public List<Notification> getAllNotifications() {
	        return notificationRepository.findAll();
	    }

	    public Notification getNotificationById(Long id) {
	        return notificationRepository.findById(id).orElse(null);
	    }
}
