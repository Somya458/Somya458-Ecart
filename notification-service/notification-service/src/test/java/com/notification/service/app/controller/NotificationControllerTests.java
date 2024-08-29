package com.notification.service.app.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.notification.service.app.model.Notification;
import com.notification.service.app.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTests {

    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void testSendNotification() throws Exception {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test message");
        notification.setStatus("Pending");

        when(notificationService.sendNotification(any(Notification.class))).thenReturn(notification);

        mockMvc.perform(post("/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"message\":\"Test message\",\"status\":\"Pending\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.message", is("Test message")))
                .andExpect(jsonPath("$.status", is("Pending")));
    }

    @Test
    public void testGetAllNotifications() throws Exception {
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setMessage("Test message 1");
        notification1.setStatus("Pending");

        Notification notification2 = new Notification();
        notification2.setId(2L);
        notification2.setMessage("Test message 2");
        notification2.setStatus("Delivered");

        List<Notification> notifications = Arrays.asList(notification1, notification2);

        when(notificationService.getAllNotifications()).thenReturn(notifications);

        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].message", is("Test message 1")))
                .andExpect(jsonPath("$[0].status", is("Pending")))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].message", is("Test message 2")))
                .andExpect(jsonPath("$[1].status", is("Delivered")));
    }

    @Test
    public void testGetNotificationById() throws Exception {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test message");
        notification.setStatus("Pending");

        when(notificationService.getNotificationById(1L)).thenReturn(notification);

        mockMvc.perform(get("/notifications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.message", is("Test message")))
                .andExpect(jsonPath("$.status", is("Pending")));
    }
}
