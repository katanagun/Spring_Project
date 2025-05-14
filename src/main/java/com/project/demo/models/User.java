package com.project.demo.models;

public class User {
    long idUser;
    long idTask;
    String nameUser;

    public User(long idUser, String nameUser){
        this.idUser = idUser;
        this.idTask = 0;
        this.nameUser = nameUser;
    }

    public User(){

    }

    public long getIdUser(){
        return this.idUser;
    }

    public void setIdUser(long idUser){
        this.idUser = idUser;
    }

    public long getIdTask(){
        return this.idTask;
    }

    public void setIdTask(long idTask){
        this.idTask = idTask;
    }

    public String getNameUser(){
        return this.nameUser;
    }

    public void setNameUser(String nameUser){
        this.nameUser = nameUser;
    }
}