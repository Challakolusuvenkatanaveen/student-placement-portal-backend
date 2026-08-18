package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Notification;

public interface NotificationService {

    Notification createNotification(Notification notification);

    List<Notification> getStudentNotifications(Long studentId);

    Notification markAsRead(Long notificationId);

    void deleteNotification(Long notificationId);

}