package com.project.demo.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @jakarta.persistence.Id
    @Column(name = "NotificationId")
    private Long notificationId;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "TaskId")
    private Long taskId;

    @Column(name = "NotificationValue")
    private String notificationValue;
}