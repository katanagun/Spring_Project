package com.project.demo.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.services.ModelNotificationService;
import com.project.demo.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TaskEventConsumer {

    private final ModelNotificationService notificationService;

    public TaskEventConsumer(ModelNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "task-events", groupId = "notification-group")
    public void listen(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(message);

            Long taskId = json.get("taskId").asLong();
            Long userId = json.get("userId").asLong();
            String event = json.get("event").asText();

            notificationService.updateNotification(userId, taskId, event);
        } catch (Exception e) {
            System.err.println("Error processing Kafka-message: " + e.getMessage());
        }
    }
}

