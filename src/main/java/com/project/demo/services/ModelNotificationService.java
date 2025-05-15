package com.project.demo.services;

import com.project.demo.models.Notification;

import java.util.Collection;
import java.util.List;

public interface ModelNotificationService {
    public Collection<Notification> getUserNotifications(long idUser);
    public Collection<Notification> getAllNotifications();

}