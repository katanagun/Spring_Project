package com.project.demo.Exceptions;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(Long taskId, Long userId) {
        super("Notification not found for taskId: " + taskId + " and userId: " + userId);
    }
}
