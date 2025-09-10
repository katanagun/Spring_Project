package com.project.demo.models;

import lombok.Data;

@Data
public class Notification {
    long idUser;
    long idTask;
    String value;

    public Notification(long idUser, long idTask, String value){
        this.idUser= idUser;
        this.idTask = idTask;
        this.value = value;
    }

}

