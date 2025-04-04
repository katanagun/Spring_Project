package com.project.demo.models;

public class User {
    long idUser;
    String nameUser;

    public User(long idUser, String nameUser){
        this.idUser = idUser;
        this.nameUser = nameUser;
    }

    public long getIdUser(){
        return this.idUser;
    }

    public void setIdUser(long idUser){
        this.idUser = idUser;
    }

    public String getNameUser(){
        return this.nameUser;
    }

    public void setNameUser(String nameUser){
        this.nameUser = nameUser;
    }
}
