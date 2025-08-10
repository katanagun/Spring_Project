package com.project.demo.db.JPA;

import com.project.demo.db.Notification;
import com.project.demo.db.repositories.NotificationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Profile("JPA")
public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
}