package com.notification.service.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.notification.service.app.model.Notification;
import com.notification.service.app.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NotificationServiceTests {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendNotification() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test message");
        notification.setStatus("Pending");

        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification savedNotification = notificationService.sendNotification(notification);
        assertThat(savedNotification).isNotNull();
        verify(notificationRepository, times(1)).save(notification);
    }

    @Test
    public void testGetAllNotifications() {
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setMessage("Test message 1");
        notification1.setStatus("Pending");

        Notification notification2 = new Notification();
        notification2.setId(2L);
        notification2.setMessage("Test message 2");
        notification2.setStatus("Delivered");

        List<Notification> notifications = Arrays.asList(notification1, notification2);

        when(notificationRepository.findAll()).thenReturn(notifications);

        List<Notification> fetchedNotifications = notificationService.getAllNotifications();
        assertThat(fetchedNotifications).hasSize(2);
        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    public void testGetNotificationById() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test message");
        notification.setStatus("Pending");

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        Notification fetchedNotification = notificationService.getNotificationById(1L);
        assertThat(fetchedNotification).isNotNull();
        verify(notificationRepository, times(1)).findById(1L);
    }
}
