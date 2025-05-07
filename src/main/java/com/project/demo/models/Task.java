package com.project.demo.models;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Task {
    long idTask;
    String valueTask;
    ZonedDateTime creationDate;
    ZonedDateTime targetDate;
}
