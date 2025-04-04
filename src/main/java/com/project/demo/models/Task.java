package com.project.demo.models;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Task {
    long idTask;
    long idUser;
    String valueTask;
    ZonedDateTime creationDate;
    ZonedDateTime targetDate;

    public Task(long idTask, long idUser, String valueTask, ZonedDateTime targetDate){
        this.idTask = idTask;
        this.idUser = idUser;
        this.valueTask = valueTask;
        this.creationDate = ZonedDateTime.now();
        this.targetDate = targetDate;
    }
}