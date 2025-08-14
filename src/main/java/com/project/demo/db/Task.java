package com.project.demo.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

import java.io.Serializable;

@Entity
@Table(name = "Tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
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
