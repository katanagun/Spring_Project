package com.project.demo.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "Tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @jakarta.persistence.Id
    @Column(name = "TaskId")
    private Long taskId;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "TaskValue")
    private String taskValue;

    @Column(name = "CreationDate")
    private ZonedDateTime creationDate = ZonedDateTime.now();

    @Column(name = "TargetDate")
    private ZonedDateTime targetDate;

}