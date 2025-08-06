package com.project.demo.db.InMemory;

import com.project.demo.db.Notification;
import com.project.demo.db.repositories.NotificationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("InMemory")
public class NotificationInMemoryRepository implements NotificationRepository {
    public Map<Long, Notification> notifications = new HashMap<>();

    @Override
    public Collection<Notification> findByUserId(Long idUser) {
        return notifications.values().stream()
                .filter(notification -> Objects.equals(notification.getUserId(), idUser))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Notification> findAll() {
        return notifications.values();
    }

    @Override
    public void saveNotification(Notification notification) {
        Long id = notification.getNotificationId();
        notifications.put(id, notification);
    }
}
