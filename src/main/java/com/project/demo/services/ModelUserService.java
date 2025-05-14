package com.project.demo.services;

import com.project.demo.models.User;

public interface ModelUserService {
    public boolean existUser(long idUser);
    public void insertUser(long idUser, String nameUser);
}
