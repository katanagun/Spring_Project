package com.project.demo.db.JPA;

import com.project.demo.db.Notification;
import com.project.demo.db.repositories.NotificationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("JPA")
public class NotificationJpaAdapter implements NotificationRepository {
    private final NotificationJpaRepository jpaRepository;

    public NotificationJpaAdapter(NotificationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Collection<Notification> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public Collection<Notification> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void saveNotification(Notification notification) {
        jpaRepository.save(notification);
    }
}
