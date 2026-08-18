package com.example.demo.ServiceImp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Notification;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.NotificationRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private StudentRepository studentRepository;

    // =====================================
    // Create Notification
    // =====================================

    @Override
    public Notification createNotification(Notification notification) {

        Student student = studentRepository.findById(
                notification.getStudent().getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        notification.setStudent(student);

        // Correct setter
        notification.setCreatedAt(LocalDateTime.now());

        notification.setIsRead(false);

        return notificationRepository.save(notification);
    }

    // =====================================
    // Get Student Notifications
    // =====================================

    @Override
    public List<Notification> getStudentNotifications(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        return notificationRepository.findByStudentStudentId(studentId);
    }

    // =====================================
    // Mark Notification As Read
    // =====================================

    @Override
    public Notification markAsRead(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification Not Found"));

        notification.setIsRead(true);

        return notificationRepository.save(notification);
    }

    // =====================================
    // Delete Notification
    // =====================================

    @Override
    public void deleteNotification(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification Not Found"));

        notificationRepository.delete(notification);
    }
}