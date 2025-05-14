package com.project.demo.services;

import com.project.demo.models.User;

public interface ModelUserService {
    public boolean getUser(long idUser);
    public void putUser(User user);
}